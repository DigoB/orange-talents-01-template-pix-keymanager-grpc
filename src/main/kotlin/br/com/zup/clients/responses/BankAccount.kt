package br.com.zup.clients.responses

data class BankAccount(

    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType
)