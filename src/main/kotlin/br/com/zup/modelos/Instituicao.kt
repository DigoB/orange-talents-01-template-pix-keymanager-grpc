package br.com.zup.modelos

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "instituicao")
class Instituicao(

    @Column(nullable = false)
    @field:NotBlank
    val nome: String,
    @Column(nullable = false)
    @field:NotBlank
    val ispb: String
) {
    @Column(nullable = false)
    @Id
    @GeneratedValue
    var id: Long? = null
}
