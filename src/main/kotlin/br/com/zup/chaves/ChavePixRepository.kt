package br.com.zup.chaves

import br.com.zup.chaves.ChavePix
import br.com.zup.chaves.lista.ListaPix
import br.com.zup.contas.Titular
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long> {

    fun existsByChave(chave: String?): Boolean

    @Query("SELECT c FROM ChavePix c WHERE c.chave = :chave")

    fun findByChave(chave: String?): Optional<ChavePix>

    fun findById(chaveId: String): Optional<ChavePix>

    @Query("select c from ChavePix c where c.conta.titular.id = :titularId")
    fun findByTitularId(titularId: String): List<ChavePix>
//
//    @Query("select * from ChavePix c where c.id = :clienteId")
//    fun findAllByClienteId(clienteId: UUID?): List<ChavePix>

}