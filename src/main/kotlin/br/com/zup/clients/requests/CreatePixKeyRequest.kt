package br.com.zup.clients.requests

import br.com.zup.clients.responses.BankAccount
import br.com.zup.clients.responses.KeyType
import br.com.zup.clients.responses.Owner

class CreatePixKeyRequest(
    var keyType: KeyType,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
)