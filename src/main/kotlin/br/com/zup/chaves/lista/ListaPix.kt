package br.com.zup.chaves.lista

import br.com.zup.validations.ValidUUID
import javax.validation.constraints.NotBlank

class ListaPix(@field:ValidUUID
               @field:NotBlank val clienteId: String
)