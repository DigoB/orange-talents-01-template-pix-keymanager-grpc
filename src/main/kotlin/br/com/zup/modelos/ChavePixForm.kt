package br.com.zup.modelos

import br.com.zup.TipoDaChave
import br.com.zup.TipoDaConta
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

//@Instrospected
data class ChavePixForm(@field:NotBlank
                        val clientId: String,
                        @field:NotNull
                        val tipoDaChave: TipoDaChave?,
                        @field:Size(max = 77)
                        val chave: String,
                        @field:NotNull
                        val tipoDaConta: TipoDaConta?
)