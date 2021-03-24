package br.com.zup.repositories

import br.com.zup.modelos.Conta
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ContaRepository: JpaRepository<Conta, Long> {
    fun findById(id: UUID): Optional<Conta>

    @Query("SELECT c FROM Conta c WHERE c.titular.id = :clienteId")
    fun findByClienteId(clienteId: String?): Optional<Conta>

}