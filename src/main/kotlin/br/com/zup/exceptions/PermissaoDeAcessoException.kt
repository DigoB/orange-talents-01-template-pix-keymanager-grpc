package br.com.zup.exceptions

import java.lang.RuntimeException

class PermissaoDeAcessoException(message: String = "Acesso Negado") : RuntimeException(message)