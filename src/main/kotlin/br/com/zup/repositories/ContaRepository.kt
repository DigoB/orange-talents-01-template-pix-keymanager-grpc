package br.com.zup.repositories

import br.com.zup.modelos.Conta
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ContaRepository: JpaRepository<Conta, Long>