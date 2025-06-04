package com.microservico.clienteservice.domain.model;

import com.microservico.clienteservice.domain.model.Cliente;
import com.microservico.clienteservice.domain.model.Endereco;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComGettersESetters() {
        Endereco endereco = new Endereco("Rua A", "Cidade A", "Estado A", "12345-000");
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setEndereco(endereco);

        assertEquals("1", cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
        assertEquals("Rua A", cliente.getEndereco().getRua());
    }

    @Test
    void deveCriarClienteComConstrutorCompleto() {
        Endereco endereco = new Endereco("Rua B", "Cidade B", "Estado B", "54321-000");
        Cliente cliente = new Cliente("2", "Maria", "09876543210", LocalDate.of(1985, 5, 10), endereco);

        assertEquals("2", cliente.getId());
        assertEquals("Maria", cliente.getNome());
        assertEquals("09876543210", cliente.getCpf());
        assertEquals("Rua B", cliente.getEndereco().getRua());
    }
}

