package br.com.zup.validations

//@MustBeDocumented
//@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
//@Retention(AnnotationRetention.RUNTIME)
//@Constraint(validatedBy = [ValidPixKeyValidator::class])
//annotation class ValidPixKey(
//    val message: String = "chave inválida",
//    val groups: Array<KClass<Any>> = [],
//    val payload: Array<KClass<Payload>> = []
//)
//
//class ValidPixKeyValidator: ConstraintValidator<ValidPixKey, NovaChavePix> {
//    override fun isValid(value: NovaChavePix?, context: ConstraintValidatorContext?): Boolean {
//        if(value?.tipoDaChave == null)
//            return false
//        else
//            return value.tipoDaChave.valida(value?.chave)
//    }
//
//
//}