package br.com.zup.chaves.consulta

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class ConsultaChaveRequest(
    @field:NotNull val chaveId: Long,
    @field:NotBlank val clienteId: String
)