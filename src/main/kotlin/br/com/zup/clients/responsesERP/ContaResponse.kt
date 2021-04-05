package br.com.zup.clients.responsesERP

import br.com.zup.contas.TipoConta

data class ContaResponse(

    val agencia: String,
    val numero: String,
    val tipo: TipoConta,
    val instituicao: InstituicaoResponse,
    val titular: TitularResponse
)