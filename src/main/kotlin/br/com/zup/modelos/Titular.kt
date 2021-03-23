package br.com.zup.modelos


import javax.persistence.*

@Entity
class Titular(
    @field:Id val id: String,
    val nome: String?,
    val cpf: String?
)