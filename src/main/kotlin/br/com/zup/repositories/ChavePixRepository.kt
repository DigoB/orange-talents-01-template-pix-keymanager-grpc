package br.com.zup.repositories

import br.com.zup.modelos.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, UUID> {
    fun existsByChave(chave: String?): Boolean
}