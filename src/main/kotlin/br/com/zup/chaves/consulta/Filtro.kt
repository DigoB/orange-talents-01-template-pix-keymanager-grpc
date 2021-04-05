package br.com.zup.chaves.consulta

import br.com.zup.chaves.ChavePix
import br.com.zup.chaves.ChavePixRepository
import br.com.zup.clients.ItauClient
import br.com.zup.exceptions.ChaveNaoEncontradaException
import br.com.zup.exceptions.PermissaoDeAcessoException
import br.com.zup.validations.ValidUUID
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
sealed class Filtro {

    abstract fun filtra(chavePixRepository: ChavePixRepository, itauClient: ItauClient): ChavePix

    @Introspected
    data class PorPixId(
        @field:NotBlank @field:ValidUUID val clienteId: String,
        @field:NotNull val chaveId: String
    ): Filtro() {

        fun chaveId() = chaveId

        fun clienteId() = UUID.fromString(clienteId)

        override fun filtra(chavePixRepository: ChavePixRepository, itauClient: ItauClient):
                ChavePix {
            val possivelChave = chavePixRepository.findById(chaveId)
            if(possivelChave.isEmpty){
                throw ChaveNaoEncontradaException("chave pix ${chaveId} não encontrada")
            }
            val chavePix = possivelChave.get()
            if(!chavePix.pertence(clienteId)){
                throw PermissaoDeAcessoException("Você não tem permissao para acesar o recurso")
            }

            return chavePix
        }
    }

    @Introspected
    data class PorChave(@field:NotBlank @Size(max=77) val chave: String)
        : Filtro(){
        override fun filtra(chavePixRepository: ChavePixRepository, itauClient: ItauClient): ChavePix {
            val possivelChave = chavePixRepository.findByChave(chave)
            if(possivelChave.isEmpty){
                throw ChaveNaoEncontradaException("chave pix ${chave} não encontrada")
            }
            val chavePix = possivelChave.get()
            val consultaChave = itauClient.consulta(chave)

            if(consultaChave.status != HttpStatus.OK){
                throw java.lang.IllegalStateException("Chave pix $chave inválida")
            }

            return chavePix
        }

    }

    @Introspected
    class Invalido(): Filtro(){
        override fun filtra(chavePixRepository: ChavePixRepository, itauClient: ItauClient): ChavePix {
            throw IllegalStateException("Filtro inválido")
        }

    }

}
