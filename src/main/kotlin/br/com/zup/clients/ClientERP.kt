package br.com.zup.clients

import br.com.zup.clients.responses.AlgumaResponse
import br.com.zup.clients.responses.ErpResponse
import io.micronaut.data.annotation.Query
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.awt.PageAttributes

@Client("http://localhost:9091")
interface ClientERP {

    @Get(
        "/api/v1/clientes/{clienteId}",
        consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON]
    )
    fun buscaClienteERP(@PathVariable clienteId: String): ErpResponse

    @Get(
        "/api/v1/clientes/{clienteId}/contas",
        consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON]
    )
    fun buscaContaERP(@PathVariable clienteId: String, @QueryValue tipo: String) : AlgumaResponse
}