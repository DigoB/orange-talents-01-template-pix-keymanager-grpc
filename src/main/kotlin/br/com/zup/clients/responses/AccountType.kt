package br.com.zup.clients.responses

import br.com.zup.modelos.TipoConta

enum class AccountType {
    CACC, SVGS;

    companion object {
        fun getAccountType(tipoConta: TipoConta): AccountType {
            if("CONTA_CORRENTE".equals(tipoConta.name)){
                return CACC
            }
            return SVGS
        }
    }
}