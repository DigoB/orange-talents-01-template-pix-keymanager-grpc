package br.com.zup

import br.com.zup.clients.responsesERP.BuscaPorClienteResponse
import br.com.zup.modelos.*

fun CadastraPixRequest.toModel(): ChavePix {
    return ChavePix(
        chave = this.chave,
        tipoChave = this.tipoDaChave,
        tipoConta = this.tipoDaConta
    )
}

fun CadastraPixRequest.paraChavePixForm(): ChavePixForm {
    return ChavePixForm(clienteId, tipoDaChave, chave, tipoDaConta)
}

fun BuscaPorClienteResponse.toModel(): Conta {
    return Conta(
        agencia, numero, Instituicao(instituicao.nome, instituicao.ispb), Titular(
            nome = titular.nome,
            cpf =titular.cpf,
            id = titular.id
        ), tipo
    )
}