package br.com.zup.services
//
//import br.com.zup.PixGrpcRequest
//import br.com.zup.TipoDaChave
//import br.com.zup.TipoDaConta
//import br.com.zup.modelos.NovaChavePix
//
//fun PixGrpcRequest.paraChave() : NovaChavePix {
//    return NovaChavePix(
//        clientId = clientId,
//        tipoDaChave = when(tipoDaChave) {
//            TipoDaChave.DESCONHECIDO -> null
//            else -> TipoDaChave.valueOf(tipoDaChave.name)
//        },
//        chave = chave,
//        tipoDaConta = when(tipoDaConta) {
//            TipoDaConta.DESCONHECIDO_TIPO_CONTA -> null
//            else -> TipoDaConta.valueOf(tipoDaConta.name)
//        }
//    )
//}