package br.com.zup.exceptions
//
//import io.grpc.Status
//import io.micronaut.core.exceptions.ExceptionHandler
//
//class ChavePixNaoEncontradaExceptionHandler : ExceptionHandler<ChaveNaoEncontradaException> {
//    override fun handle(e: ChaveNaoEncontradaException?): StatusWithDetails {
//
//        return Status.NOT_FOUND.withDescription(e.message).withCause(e)
//    }
//
//    override fun supports(e: Exception): Boolean {
//        return e is ChaveNaoEncontradaException
//    }
//}