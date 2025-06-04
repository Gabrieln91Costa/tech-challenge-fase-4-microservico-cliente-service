package com.microservico.clienteservice.application.service;

import com.microservico.clienteservice.domain.exception.CpfJaCadastradoException;
import com.microservico.clienteservice.domain.model.Cliente;
import com.microservico.clienteservice.domain.model.Endereco;
import com.microservico.clienteservice.domain.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Inicializa os mocks do Mockito no JUnit5
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveAtualizarClienteComSucesso() {
        String id = "abc123";

        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id);
        clienteExistente.setNome("Nome Antigo");
        clienteExistente.setDataNascimento(LocalDate.of(1990, 1, 1));
        clienteExistente.setEndereco(new Endereco("Rua Antiga", "Cidade", "Estado", "12345-000"));

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Novo Nome");
        clienteAtualizado.setDataNascimento(LocalDate.of(2000, 1, 1));
        clienteAtualizado.setEndereco(new Endereco("Nova Rua", "Cidade Nova", "Estado Novo", "54321-000"));

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cliente resultado = clienteService.atualizarCliente(id, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals(LocalDate.of(2000, 1, 1), resultado.getDataNascimento());
        assertEquals("Nova Rua", resultado.getEndereco().getRua());

        verify(clienteRepository).findById(id);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        String id = "inexistente";
        Cliente cliente = new Cliente();

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            clienteService.atualizarCliente(id, cliente);
        });

        assertEquals("Cliente não encontrado", ex.getMessage());
        verify(clienteRepository).findById(id);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deveCriarClienteComCpfNaoCadastrado() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("Fulano");

        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.criarCliente(cliente);

        assertNotNull(resultado);
        assertEquals("Fulano", resultado.getNome());

        verify(clienteRepository).findByCpf("12345678900");
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");

        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.of(cliente));

        assertThrows(CpfJaCadastradoException.class, () -> {
            clienteService.criarCliente(cliente);
        });

        verify(clienteRepository).findByCpf("12345678900");
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deveBuscarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setId("id123");

        when(clienteRepository.findById("id123")).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.porId("id123");

        assertTrue(resultado.isPresent());
        assertEquals("id123", resultado.get().getId());
    }

    @Test
    void deveBuscarClientePorCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");

        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.porCpf("12345678900");

        assertTrue(resultado.isPresent());
        assertEquals("12345678900", resultado.get().getCpf());
    }

    @Test
    void deveListarTodosClientes() {
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cliente> resultado = clienteService.listarTodos();

        assertEquals(2, resultado.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void deveVerificarExistenciaDeClientePorCpf() {
        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.of(new Cliente()));

        boolean existe = clienteService.clienteExistePorCpf("12345678900");

        assertTrue(existe);
        verify(clienteRepository).findByCpf("12345678900");
    }

    @Test
    void deveRetornarFalseSeClienteNaoExistePorCpf() {
        when(clienteRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        boolean existe = clienteService.clienteExistePorCpf("12345678900");

        assertFalse(existe);
    }
}
