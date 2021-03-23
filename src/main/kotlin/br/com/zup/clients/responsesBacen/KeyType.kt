package br.com.zup.clients.responsesBacen

import br.com.zup.TipoDaChave
import io.grpc.Status
import java.util.regex.Pattern


// TODO: 23/03/2021 Verificar outra forma de validar o tipo de chave pelo BeanValidation
//enum class KeyType(val status: Status, val mensagem: String) {
//
//    CPF(Status.INVALID_ARGUMENT, "cpf inválido") {
//        override fun isValid(chave: String): Boolean {
//            return chave.matches("^[0-9]{11}$".toRegex())
//        }
//    }, CNPJ(Status.INVALID_ARGUMENT, "cnpj inválido") {
//        override fun isValid(chave: String): Boolean {
//            return chave.matches("(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}\$)".toRegex())
//        }
//    }, PHONE(Status.INVALID_ARGUMENT, "telefone inválido") {
//        override fun isValid(chave: String): Boolean {
//            return chave.matches("^\\+[1-9][0-9]\\d{1,14}$".toRegex())
//        }
//    }, EMAIL(Status.INVALID_ARGUMENT, "email inválido") {
//        override fun isValid(chave: String): Boolean {
//            return Pattern.compile(
//                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
//                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
//                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
//            ).matcher(chave).matches()
//        }
//    }, RANDOM(Status.INVALID_ARGUMENT, "chave aleatória inválida") {
//        override fun isValid(chave: String): Boolean {
//            return chave.length <= 77
//        }
//    };
//
//    companion object {
//        fun getKeyType(tipoDaChave: TipoDaChave): KeyType {
//            if (CPF.name.equals(tipoDaChave.name)){
//                return CPF
//            }else if(CNPJ.name.equals(tipoDaChave.name)){
//                return CNPJ
//            }else if("TELEFONE_CELULAR".equals(tipoDaChave.name.toString())){
//                return PHONE
//            }else if(EMAIL.name.equals(tipoDaChave.name)){
//                return EMAIL
//            }
//
//            return RANDOM
//        }
//    }
//
//    abstract fun isValid(chave: String): Boolean;
//
//}