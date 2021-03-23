package br.com.zup.clients.responsesBacen

import br.com.zup.clients.responsesERP.Owner
import java.time.LocalDateTime

class CreatePixKeyResponse(
    val keyType: KeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
)