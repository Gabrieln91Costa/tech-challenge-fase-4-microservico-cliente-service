package com.microservico.clienteservice.domain.model;

import com.microservico.clienteservice.domain.model.Cliente;
import com.microservico.clienteservice.domain.model.Endereco;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveTestarGettersESetters() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua 1");
        endereco.setCidade("Cidade 1");
        endereco.setEstado("Estado 1");
        endereco.setCep("11111-000");

        assertEquals("Rua 1", endereco.getRua());
        assertEquals("Cidade 1", endereco.getCidade());
        assertEquals("Estado 1", endereco.getEstado());
        assertEquals("11111-000", endereco.getCep());
    }

    @Test
    void deveTestarConstrutorCompleto() {
        Endereco endereco = new Endereco("Rua 2", "Cidade 2", "Estado 2", "22222-000");

        assertEquals("Rua 2", endereco.getRua());
        assertEquals("Cidade 2", endereco.getCidade());
        assertEquals("Estado 2", endereco.getEstado());
        assertEquals("22222-000", endereco.getCep());
    }
}
