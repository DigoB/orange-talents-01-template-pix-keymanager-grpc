package br.com.zup.modelos

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "instituicao")
class Instituicao(

    val nome: String,
    val isbn: String?
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
