package com.microservico.clienteservice.infrastructure.handler;

import com.microservico.clienteservice.domain.exception.CpfJaCadastradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GlobalExceptionHandlerTest.TestController.class)
@Import(GlobalExceptionHandler.class)
@ComponentScan(basePackageClasses = GlobalExceptionHandlerTest.TestController.class) // força scan do controller interno
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @RestController
    static class TestController {
        @GetMapping("/teste-exception")
        public void throwException() {
            throw new CpfJaCadastradoException();
        }
    }

    @Test
    void deveRetornarBadRequestComMensagemAoLancarCpfJaCadastradoException() throws Exception {
        mockMvc.perform(get("/teste-exception")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("O CPF informado já está cadastrado. Por favor, verifique e tente novamente."));
    }
}
