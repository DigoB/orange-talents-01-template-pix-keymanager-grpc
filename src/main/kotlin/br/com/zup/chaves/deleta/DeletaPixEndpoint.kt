package br.com.zup.chaves.deleta

import br.com.zup.DeletaPixRequest
import br.com.zup.DeletaPixResponse
import br.com.zup.DeletaPixServiceGrpc
import br.com.zup.exceptions.ErrorHandler
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.transaction.Transactional

@ErrorHandler
@Singleton
class DeletaPixEndpoint(
    val service: DeletaChaveService
) : DeletaPixServiceGrpc.DeletaPixServiceImplBase() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun deletaPix(
        request: DeletaPixRequest?,
        responseObserver: StreamObserver<DeletaPixResponse>?
    ) {

        logger.info("Recebendo request: $request")

        service.deletaChave(request!!.toModel())

        logger.info("Setando parametros do response para deletar")
        responseObserver!!.onNext(DeletaPixResponse.newBuilder()
                        .setClientId(request.clientId)
                        .setPixId(request.pixId)
                        .build())
        responseObserver.onCompleted()

    }

}

fun DeletaPixRequest.toModel() : br.com.zup.clients.requests.DeletaPixRequest {
    return br.com.zup.clients.requests.DeletaPixRequest(chaveId = this.pixId, clienteId = this.clientId)
}