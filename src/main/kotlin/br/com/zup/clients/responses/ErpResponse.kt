package br.com.zup.clients.responses

import br.com.zup.modelos.Instituicao
import java.util.*

class ErpResponse(
    val id: UUID,
    val nome: String,
    val cpf: String,
    val instituicao: InstituicaoResponse
)
