package br.com.zup.chaves.lista

import br.com.zup.chaves.ChavePix
import br.com.zup.chaves.ChavePixRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.validation.Valid

@Validated
@Singleton
class ListaPixService(val chavePixRepository: ChavePixRepository) {

    fun lista(@Valid dados: ListaPix?) : List<ChavePix> {
        return chavePixRepository.findByTitularId(dados?.clienteId!!)
    }

}
