package br.com.zup

import br.com.zup.chaves.ChavePix
import br.com.zup.chaves.ChavePixForm
import br.com.zup.clients.responsesERP.BuscaPorClienteResponse
import br.com.zup.contas.Conta
import br.com.zup.contas.Instituicao
import br.com.zup.contas.Titular

fun CadastraPixRequest.toModel(conta: Conta): ChavePix {
    return ChavePix(
        chave = this.chave,
        tipoChave = this.tipoDaChave,
        tipoConta = this.tipoDaConta,
        conta = conta)
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