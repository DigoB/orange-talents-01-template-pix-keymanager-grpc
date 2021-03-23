package br.com.zup.modelos

import javax.persistence.*

@Entity
class Conta(
    @Column(nullable = false)
    var agencia: String?,

    @Column(nullable = false)
    var numero: String?,

    @field:ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var instituicao: Instituicao?,

    @field:ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var titular: Titular?,

//    @field:Enumerated(EnumType.STRING)
    var tipo: String
) {
    @Column(nullable = false)
    @Id
    @GeneratedValue
    var id: Long? = null
}