package br.com.zup.clients.responses

data class Owner(

    val type: Type,
    val name: String,
    val taxIdNumber: String
)

enum class Type {
    NATURAL_PERSON,
    LEGAL_PERSON
}