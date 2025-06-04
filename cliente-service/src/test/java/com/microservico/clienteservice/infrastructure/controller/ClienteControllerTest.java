package com.microservico.clienteservice.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservico.clienteservice.application.service.ClienteService;
import com.microservico.clienteservice.domain.model.Cliente;
import com.microservico.clienteservice.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId("1");
        cliente.setNome("João Silva");
        cliente.setCpf("12345678900");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setEndereco(new Endereco("Rua 1", "Cidade", "Estado", "12345-000"));
    }

    @Test
    void deveCriarCliente() throws Exception {
        when(clienteService.criarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("João Silva")));
    }

    @Test
    void deveBuscarClientePorId() throws Exception {
        when(clienteService.porId(anyString())).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/clientes/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("12345678900")));
    }

    @Test
    void deveRetornarNotFoundSeIdNaoExiste() throws Exception {
        when(clienteService.porId(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/clientes/id/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveVerificarClientePorCpfExistente() throws Exception {
        when(clienteService.clienteExistePorCpf(anyString())).thenReturn(true);

        mockMvc.perform(get("/clientes/12345678900"))
                .andExpect(status().isOk());
    }

    @Test
    void deveVerificarClientePorCpfInexistente() throws Exception {
        when(clienteService.clienteExistePorCpf(anyString())).thenReturn(false);

        mockMvc.perform(get("/clientes/00000000000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        when(clienteService.atualizarCliente(anyString(), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/clientes/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("João Silva")));
    }

    @Test
    void deveListarClientes() throws Exception {
        when(clienteService.listarTodos()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf", is("12345678900")));
    }
}
