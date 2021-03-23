package br.com.zup.clients.responsesERP

import java.util.*

class BuscaPorClienteResponse(
    val tipo: String,
    val instituicao: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
)

class ErpResponse(
    val id: UUID,
    val nome: String,
    val cpf: String,
    val instituicao: InstituicaoResponse
)