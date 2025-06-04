package com.microservico.clienteservice.domain.exception;

import com.microservico.clienteservice.domain.exception.CpfJaCadastradoException; // importar a classe original

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpfJaCadastradoExceptionTest {

    @Test
    void deveLancarCpfJaCadastradoExceptionComMensagemPadrao() {
        CpfJaCadastradoException exception = new CpfJaCadastradoException();
        assertEquals("O CPF informado já está cadastrado. Por favor, verifique e tente novamente.", exception.getMessage());
    }
}
