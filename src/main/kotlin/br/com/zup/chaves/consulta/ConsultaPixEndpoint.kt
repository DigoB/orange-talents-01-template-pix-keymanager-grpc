package br.com.zup.chaves.consulta

import br.com.zup.*
import br.com.zup.chaves.ChavePixRepository
import br.com.zup.clients.ItauClient
import br.com.zup.exceptions.ErrorHandler
import com.google.protobuf.Timestamp
import io.grpc.stub.StreamObserver
import io.micronaut.validation.validator.Validator
import java.time.ZoneId
import javax.inject.Singleton
import javax.validation.ConstraintViolationException


@ErrorHandler
@Singleton
class ConsultaPixEndpoint(
    private val chavePixRepository: ChavePixRepository,
    private val itauClient: ItauClient,
    private val validator: Validator
) : ConsultaPixServiceGrpc.ConsultaPixServiceImplBase() {

    override fun consultaPix(
        request: ConsultaPixRequest?,
        responseObserver: StreamObserver<ConsultaPixResponse>?
    ) {
        val filtro = request?.toModel(validator)
        val chaveInfo = filtro?.filtra(chavePixRepository, itauClient)

        val chaveId = request?.pixId
        responseObserver?.onNext(ConsultaPixResponse.newBuilder()
            .setClienteId(chaveId?.clienteId.toString()) // Protobuf usa "" como valor defaut
            .setPixId(chaveId?.pixId.toString())
            .setChave(ConsultaPixResponse.ChavePix.newBuilder()
                .setTipoDaChave(TipoDaChave.valueOf(chaveInfo?.tipoChave!!.name).toString())
                .setChave(chaveInfo.chave)
                .setConta(ConsultaPixResponse.ChavePix.ContaInfo.newBuilder()
                    .setTipoDaConta(TipoDaConta.valueOf(chaveInfo.tipoConta.name).toString())
                    .setInstituicao(chaveInfo.conta.instituicao?.nome)
                    .setNomeDoTitular(chaveInfo.conta.titular?.nome)
                    .setCpfDoTitular(chaveInfo.conta.titular?.cpf)
                    .setAgencia(chaveInfo.conta.agencia)
                    .setNumeroDaConta(chaveInfo.conta.numero)
                    .build())
                .setCriadaEm(chaveInfo.criadoEm.let {
                    val createdAt = it.atZone(ZoneId.of("UTC")).toInstant()
                    Timestamp.newBuilder()
                        .setSeconds(createdAt.epochSecond)
                        .setNanos(createdAt.nano)
                        .build()

                }))
            .build())
        responseObserver?.onCompleted()
    }

    fun ConsultaPixRequest.toModel(validator: Validator): Filtro {
        val filtro = when (filtroCase) {
            ConsultaPixRequest.FiltroCase.PIXID -> pixId.let {
                Filtro.PorPixId(clienteId = it.clienteId, chaveId = it.pixId)
            }
            ConsultaPixRequest.FiltroCase.CHAVE -> Filtro.PorChave(chave)
            ConsultaPixRequest.FiltroCase.FILTRO_NOT_SET -> Filtro.Invalido()
        }

        val violations = validator.validate(filtro)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
        return filtro
    }
}