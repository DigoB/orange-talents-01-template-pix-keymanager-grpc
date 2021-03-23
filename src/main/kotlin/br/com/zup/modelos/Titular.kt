package br.com.zup.modelos


import javax.persistence.*

@Entity
class Titular(
    val nome: String?,
    val cpf: String?
) {

    @field:Id val id: String = ""

}
