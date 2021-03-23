package br.com.zup.clients.responsesBacen

import br.com.zup.clients.responsesBacen.AccountType

data class BankAccount(

    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType
)