package br.com.zup.chaves.lista

import br.com.zup.*
import br.com.zup.chaves.ChavePixRepository
import br.com.zup.clients.ItauClient
import br.com.zup.clients.responsesBacen.CreatePixKeyResponse
import io.grpc.stub.StreamObserver
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Singleton

@Singleton
class ListaPixEndpoint(
    private val chavePixRepository: ChavePixRepository,
    private val listaPixService: ListaPixService,
    private val itauClient: ItauClient
) : ListaPixServiceGrpc.ListaPixServiceImplBase() {

    override fun lista(
        request: ListaPixRequest,
        responseObserver: StreamObserver<ListaPixResponse>
    ) {

        if (request.clienteId.isNullOrBlank())
            throw IllegalArgumentException("Id do cliente não pode ser nulo ou vazio")

        val clienteId = UUID.fromString(request.clienteId)

        val listaChaves = chavePixRepository.findByTitularId(clienteId.toString()).map {
            ChavePix.newBuilder().apply {

                pixId = it.id!!
                tipoDaChave = it.tipoChave.toString()
                chave = it.chave
                tipoDaConta = it.tipoConta.toString()
                criadaEm = it.criadoEm.toString()
            }.build()
        }

        val cliente = ListaPixResponse.newBuilder().apply {
            addAllChaves(listaChaves)
            this.clienteId = clienteId.toString()

        }.build()

        responseObserver.onNext(cliente)
        responseObserver.onCompleted()
    }
}