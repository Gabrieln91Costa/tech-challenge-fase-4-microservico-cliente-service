package com.microservico.clienteservice.infrastructure.controller;

import com.microservico.clienteservice.domain.exception.CpfJaCadastradoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestExceptionController {
    @GetMapping("/teste-exception")
    public void throwException() {
        throw new CpfJaCadastradoException();
    }
}
