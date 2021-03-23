package br.com.zup.clients.responsesBacen

import br.com.zup.TipoDaConta

enum class AccountType {
    CACC, SVGS;

    companion object {
        fun getAccountType(tipoConta: TipoDaConta): AccountType {
            if("CONTA_CORRENTE".equals(tipoConta.name)){
                return CACC
            }
            return SVGS
        }
    }
}