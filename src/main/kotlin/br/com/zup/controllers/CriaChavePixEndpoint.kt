package br.com.zup.controllers

import br.com.zup.CadastraPixRequest
import br.com.zup.CadastraPixResponse
import br.com.zup.PixGrpcServiceGrpc
import br.com.zup.TipoDaChave
import br.com.zup.clients.ChavePixClient
import br.com.zup.clients.ClientERP
import br.com.zup.clients.requests.CreatePixKeyRequest
import br.com.zup.clients.responses.*
import br.com.zup.modelos.ChavePix
import br.com.zup.modelos.TipoConta
import br.com.zup.repositories.ChavePixRepository
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class CriaChavePixEndpoint(
    val chavePixRepository: ChavePixRepository,
    val chavePixClient: ChavePixClient,
    val clientERP: ClientERP
) : PixGrpcServiceGrpc.PixGrpcServiceImplBase() {

    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    open override fun cadastraPix(request: CadastraPixRequest, responseObserver: StreamObserver<CadastraPixResponse>) {

        LOGGER.info("$request")
        try {
            val buscaClienteERP = clientERP.buscaClienteERP(request.clienteId)

            val buscaContaERP = clientERP.buscaContaERP(request.clienteId, request.tipoDaConta.toString())

            if (request.tipoDaChave.equals(TipoDaChave.CPF) && buscaClienteERP.cpf != request.chave) {
                throw IllegalArgumentException("CPF invalido")
            }

            val novaChavePix = ChavePix(
                chave = request.chave,
                tipo = request.tipoDaChave,
                conta = request.tipoDaConta,

                )

            chavePixClient.cadastra(
                CreatePixKeyRequest(
                    keyType = KeyType.CPF,
                    key = request.chave,
                    bankAccount = BankAccount(
                        participant = buscaClienteERP.instituicao.ispb,
                        buscaContaERP.agencia,
                        buscaContaERP.numero,
                        AccountType.getAccountType(TipoConta.CONTA_CORRENTE)
                    ), owner = Owner(
                        type = Type.NATURAL_PERSON,
                        name = buscaClienteERP.nome,
                        taxIdNumber = buscaClienteERP.cpf
                    )
                )
            )

            chavePixRepository.save(novaChavePix)


            val response = CadastraPixResponse.newBuilder().apply {
                clienteId = request.clienteId
                pixId = request.chave
            }.build()

            responseObserver.onNext(response)
            responseObserver.onCompleted()

        } catch (e: Exception) {
            responseObserver.onError(e)
            responseObserver.onCompleted()
        }


    }

}