package br.com.zup.modelos

import br.com.zup.TipoDaChave
import br.com.zup.TipoDaConta
import br.com.zup.clients.requests.CreatePixKeyRequest
import br.com.zup.clients.responsesBacen.AccountType
import br.com.zup.clients.responsesBacen.BankAccount
import br.com.zup.clients.responsesBacen.KeyType
import br.com.zup.clients.responsesERP.Owner
import br.com.zup.clients.responsesERP.Type
import br.com.zup.controllers.toModel
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

//@Instrospected
data class ChavePixForm(@field:NotBlank
                        val clientId: String,
                        @field:NotNull
                        val tipoDaChave: TipoDaChave,
                        @field:Size(max = 77)
                        val chave: String,
                        @field:NotNull
                        val tipoDaConta: TipoDaConta
) {
    fun paraChavePix() : ChavePix {
        return ChavePix(
            chave = this.chave,
            tipoChave = this.tipoDaChave,
            tipoConta = this.tipoDaConta
        )
    }

    fun paraChavePixRequest(conta: Conta) : CreatePixKeyRequest {
        return CreatePixKeyRequest(
            keyType = KeyType.getKeyType(this.tipoDaChave),
            key = this.chave,
            bankAccount = BankAccount(
                participant = conta.instituicao?.ispb!!,
                conta.agencia!!,
                conta.numero!!,
                AccountType.getAccountType(this.tipoDaConta)
            ), owner = Owner(
                type = Type.NATURAL_PERSON,
                name = conta.titular?.nome!!,
                taxIdNumber = conta.titular?.cpf!!
            )
        )
    }
}