package br.com.zup.modelos

import br.com.zup.TipoDaChave
import br.com.zup.TipoDaConta
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "chave_pix")
class ChavePix(
    @Column(nullable = false, unique = true)
    @field:NotBlank
    var chave: String,

    @field:NotNull
    val tipoChave: TipoDaChave,

    val tipoConta: TipoDaConta

) {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    var id: UUID? = null

    var criadoEm: LocalDateTime = LocalDateTime.now()
}