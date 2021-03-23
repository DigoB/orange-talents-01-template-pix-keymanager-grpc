package br.com.zup.clients.requests

import br.com.zup.clients.responsesBacen.BankAccount
import br.com.zup.clients.responsesBacen.KeyType
import br.com.zup.clients.responsesERP.Owner

class CreatePixKeyRequest(
    var keyType: KeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
)