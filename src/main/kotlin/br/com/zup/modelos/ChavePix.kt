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
//    @Column(nullable = false, unique = true)
    @field:NotBlank
    var chave: String,

//    @Column(nullable = false)
    @field:NotNull
    val tipo: TipoDaChave,

    val conta: TipoDaConta

) {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    var id: UUID? = null

//    @Column(nullable = false, updatable = false)
    var criadoEm: LocalDateTime = LocalDateTime.now()
}