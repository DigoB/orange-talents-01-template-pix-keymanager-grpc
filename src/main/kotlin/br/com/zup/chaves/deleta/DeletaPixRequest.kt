package br.com.zup.clients.requests

import br.com.zup.validations.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class DeletaPixRequest(
    @NotNull val chaveId: String,
    @ValidUUID @field:NotBlank val clienteId: String
)

data class DeletaPixBacenRequest(
    val key: String,
    val participant: String
)