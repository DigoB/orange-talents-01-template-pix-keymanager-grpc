package br.com.zup.chaves.consulta

import br.com.zup.chaves.ChavePix
import br.com.zup.chaves.ChavePixRepository
import br.com.zup.exceptions.ChaveNaoEncontradaException
import br.com.zup.exceptions.ErrorHandler
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.validation.Valid

@ErrorHandler
@Singleton
class ConsultaPixService(val chavePixRepository: ChavePixRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun consultaChave(@Valid request: ConsultaChaveRequest): ChavePix {

        logger.info("Buscando chave no banco de dados")
        val possivelChave = chavePixRepository.findById(request.chaveId)
        if (possivelChave.isEmpty) {
            throw ChaveNaoEncontradaException("Chave ${request.chaveId} não encontrada")
        }

        logger.info("Chave encontrada")
        val chavePix = possivelChave.get()
        return chavePix

    }

}