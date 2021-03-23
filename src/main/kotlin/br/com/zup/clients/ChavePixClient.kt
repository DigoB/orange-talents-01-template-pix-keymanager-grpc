package br.com.zup.clients

import br.com.zup.clients.requests.CreatePixKeyRequest
import br.com.zup.clients.responsesBacen.CreatePixKeyResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8082")
interface ChavePixClient {

    @Post("/api/v1/pix/keys",
        consumes = [MediaType.APPLICATION_XML], produces = [MediaType.APPLICATION_XML])
    fun cadastra(@Body createPixKeyRequest: CreatePixKeyRequest): CreatePixKeyResponse

//    @Delete("/api/v1/pix/keys/{key}",
//        consumes = [MediaType.APPLICATION_XML], produces = [MediaType.APPLICATION_XML])
//    fun deletaPix(@PathVariable chave: String)

}