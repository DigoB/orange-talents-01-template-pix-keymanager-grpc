package br.com.zup.clients

import br.com.zup.clients.responsesERP.BuscaPorClienteResponse
import br.com.zup.clients.responsesERP.ErpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:9091")
interface BacenClient {

//    @Get(
//        "/api/v1/clientes/{clienteId}",
//        consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON]
//    )
//    fun buscaClienteERP(@PathVariable clienteId: String): ErpResponse

    @Get(
        "/api/v1/clientes/{clienteId}/contas",
        consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON]
    )
    fun buscaContaERP(@PathVariable clienteId: String, @QueryValue tipo: String) : BuscaPorClienteResponse?
}