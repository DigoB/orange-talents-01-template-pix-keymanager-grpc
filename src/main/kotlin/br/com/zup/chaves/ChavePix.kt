package br.com.zup.chaves

import br.com.zup.TipoDaChave
import br.com.zup.TipoDaConta
import br.com.zup.contas.Conta
import java.time.LocalDateTime
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

    val tipoConta: TipoDaConta,

    @field:ManyToOne
    val conta: Conta

) {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    var id: Long? = null

    var criadoEm: LocalDateTime = LocalDateTime.now()

    fun pertence(clienteId: String): Boolean{
        return this.conta.titular?.id.equals(clienteId)
    }
}