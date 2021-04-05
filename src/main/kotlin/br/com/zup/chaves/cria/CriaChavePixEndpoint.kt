package br.com.zup.chaves.cria

import br.com.zup.*
import br.com.zup.clients.ItauClient
import br.com.zup.clients.BacenClient
import br.com.zup.exceptions.ErrorHandler
import br.com.zup.chaves.ChavePixRepository
import br.com.zup.contas.ContaRepository
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException

@ErrorHandler
@Singleton
class CriaChavePixEndpoint(
    val contaRepository: ContaRepository,
    val chavePixRepository: ChavePixRepository,
    val itauClient: ItauClient,
    val bacenClient: BacenClient
) : PixGrpcServiceGrpc.PixGrpcServiceImplBase() {

    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun cadastraPix(request: CadastraPixRequest, responseObserver: StreamObserver<CadastraPixResponse>) {

        LOGGER.info("$request")

        LOGGER.info("Verificando se a chave já está cadastrada")
        if (chavePixRepository.existsByChave(request.chave)) {
            throw IllegalStateException("Chave já cadastrada!")
        }

        try {

            LOGGER.info("Verificando se a conta existe")

            val possivelConta = contaRepository.findByClienteId(request.clienteId)

            val conta = if (possivelConta.isPresent) possivelConta.get()
            else bacenClient.buscaContaERP(request.clienteId, request.tipoDaConta.toString()).let {
                if (it == null) {
                    throw IllegalStateException("Conta não encontrada")
                }
                contaRepository.save(it.toModel())
            }

            LOGGER.info("Conta encontrada")

            val novaChavePix = request.paraChavePixForm()

            LOGGER.info("Salvando chave no banco de dados")
            chavePixRepository.save(novaChavePix.paraChavePix(conta))

            LOGGER.info("Cadastrando chave PIX")
            itauClient.cadastra(novaChavePix.paraChavePixRequest(conta))

        } catch (e: ConstraintViolationException) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("Request com parametros inválidos")
                    .asRuntimeException()
            )
        }

        LOGGER.info("Gerando Response do cadastro de chave")
        val response = CadastraPixResponse.newBuilder().apply {
            clienteId = request.clienteId
            pixId = request.chave
        }.build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

}