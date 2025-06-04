package com.microservico.clienteservice.domain.repository;

import com.microservico.clienteservice.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void limparBanco() {
        clienteRepository.deleteAll();  // limpa antes de cada teste para evitar dados duplicados
    }

    @Test
    @DisplayName("Deve salvar e encontrar Cliente por CPF")
    void deveSalvarEEncontrarPorCpf() {
        // cria cliente de exemplo
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("João da Silva");

        // salva cliente
        clienteRepository.save(cliente);

        // busca pelo cpf
        Optional<Cliente> encontrado = clienteRepository.findByCpf("12345678900");

        // verifica se encontrou
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("João da Silva");
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar CPF inexistente")
    void deveRetornarVazioSeCpfNaoExistir() {
        Optional<Cliente> encontrado = clienteRepository.findByCpf("00000000000");
        assertThat(encontrado).isNotPresent();
    }
}
