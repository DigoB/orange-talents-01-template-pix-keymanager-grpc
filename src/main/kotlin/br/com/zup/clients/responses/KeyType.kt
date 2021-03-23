package br.com.zup.clients.responses

import br.com.zup.TipoDaChave

enum class KeyType {

    CPF, CNPJ, PHONE, EMAIL, RANDOM;

    companion object {
        fun getKeyType(tipoDaChave: TipoDaChave): KeyType {
            if (CPF.name.equals(tipoDaChave.name)){
                return CPF
            }else if(CNPJ.name.equals(tipoDaChave.name)){
                return CNPJ
            }else if("TELEFONE_CELULAR".equals(tipoDaChave.name.toString())){
                return PHONE
            }else if(EMAIL.name.equals(tipoDaChave.name)){
                return EMAIL
            }

            return RANDOM
        }
    }

}