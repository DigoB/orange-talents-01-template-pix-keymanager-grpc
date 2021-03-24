package br.com.zup.services

import br.com.zup.clients.ChavePixClient
import br.com.zup.clients.requests.DeletaPixBacenRequest
import br.com.zup.clients.requests.DeletaPixRequest
import br.com.zup.exceptions.ChaveNaoEncontradaException
import br.com.zup.exceptions.ErrorHandler
import br.com.zup.repositories.ChavePixRepository
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@ErrorHandler
@Singleton
open class DeletaChaveService(
    val chavePixRepository: ChavePixRepository,
    val chavePixClient: ChavePixClient,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun deletaChave(@Valid deletaPixRequest: DeletaPixRequest) {

        logger.info("Buscando possivel chave no banco de dados")

        val possivelChave = chavePixRepository.findByChave(deletaPixRequest.chaveId)
        if (possivelChave.isEmpty) {
            throw ChaveNaoEncontradaException("Chave ${deletaPixRequest.chaveId} não encontrada")
        }

        logger.info("Chave encontrada")
        val chavePix = possivelChave.get()
        val request = chavePix.conta.instituicao?.ispb.let {
            DeletaPixBacenRequest(key = chavePix.chave, chavePix.conta.instituicao!!.ispb )
        }

        logger.info("Deletando chave do banco de dados")
        chavePixRepository.delete(chavePix)

        logger.info("Solicitando delete da chave no banco central")

        val response = chavePixClient.deleta(request.key, request)



    }
}
