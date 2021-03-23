package br.com.zup.endpoints

import br.com.zup.DeletaPixRequest
import br.com.zup.DeletaPixResponse
import br.com.zup.DeletaPixServiceGrpc
import br.com.zup.clients.ChavePixClient
import br.com.zup.exceptions.ErrorHandler
import br.com.zup.repositories.ChavePixRepository
import br.com.zup.services.DeletaChaveService
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.transaction.Transactional

@ErrorHandler
@Singleton
class DeletaPixEndpoint(
    val chavePixRepository: ChavePixRepository,
    val chavePixClient: ChavePixClient,
    val deletaChaveService: DeletaChaveService
) : DeletaPixServiceGrpc.DeletaPixServiceImplBase() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun deletaPix(
        request: DeletaPixRequest?,
        responseObserver: StreamObserver<DeletaPixResponse>?
    ) {

        logger.info("Recebendo request: $request")

        deletaChaveService.deleta(request?.paraDeletar())


    }
}