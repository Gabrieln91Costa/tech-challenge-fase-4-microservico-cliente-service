package com.microservico.clienteservice.domain.exception;

public class CpfJaCadastradoException extends RuntimeException {
    public CpfJaCadastradoException() {
        super("O CPF informado já está cadastrado. Por favor, verifique e tente novamente.");
    }
}
