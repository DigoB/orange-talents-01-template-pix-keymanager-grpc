package br.com.zup.endpoints

import br.com.zup.*
import br.com.zup.clients.ChavePixClient
import br.com.zup.clients.ClientERP
import br.com.zup.exceptions.ErrorHandler
import br.com.zup.repositories.ChavePixRepository
import br.com.zup.repositories.ContaRepository
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
    val chavePixClient: ChavePixClient,
    val clientERP: ClientERP
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
            else clientERP.buscaContaERP(request.clienteId, request.tipoDaConta.toString()).let {
                if (it == null) {
                    throw IllegalStateException("Conta não encontrada")
                }
                contaRepository.save(it.toModel())
            }

            LOGGER.info("Conta encontrada")

            val novaChavePix = request.paraChavePixForm()

            LOGGER.info("Salvando chave no banco de dados")
            chavePixRepository.save(novaChavePix.paraChavePix())

            LOGGER.info("Cadastrando chave PIX")
            chavePixClient.cadastra(novaChavePix.paraChavePixRequest(conta))

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