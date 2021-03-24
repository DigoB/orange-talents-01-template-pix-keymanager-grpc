package br.com.zup.repositories

import br.com.zup.modelos.ChavePix
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long> {

    fun existsByChave(chave: String?): Boolean

    @Query("SELECT c FROM ChavePix c WHERE c.chave = :chave")
    fun findByChave(chave: String?): Optional<ChavePix>

}