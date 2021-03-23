package br.com.zup.services

import br.com.zup.clients.ChavePixClient
import br.com.zup.clients.requests.DeletaPixBacenRequest
import br.com.zup.clients.requests.DeletaPixRequest
import br.com.zup.repositories.ChavePixRepository
import io.micronaut.http.HttpStatus
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
class DeletaChaveService(
    val chavePixRepository: ChavePixRepository,
    val chavePixClient: ChavePixClient
) {

    @Transactional
    fun deletaChave(@Valid deletaPixRequest: DeletaPixRequest) {

        val possivelChave = chavePixRepository.findById(deletaPixRequest.chaveId)

        if (possivelChave.isEmpty) {
            throw IllegalStateException("Chave ${deletaPixRequest.chaveId} não encontrada")
        }

        val chavePix = possivelChave.get()
//        val request = DeletaPixBacenRequest(key = chavePix.chave, participant = chavePix.)

        chavePixRepository.delete(chavePix)

//        val reponse = chavePixClient.deleta(chavePix.chave, request) {
//            if (response.status != HttpStatus.OK) {
//                val erro = response.body
//
//            }
//        }
    }

}
