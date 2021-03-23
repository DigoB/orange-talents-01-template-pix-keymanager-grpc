package br.com.zup.clients.responsesERP

import br.com.zup.clients.responsesBacen.BankAccount
import br.com.zup.clients.responsesBacen.KeyType
import java.time.LocalDateTime

class NovaChavePixFormResponse(

    val keyType: KeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
)

