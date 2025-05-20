package com.microservico.clienteservice.application.service;

import com.microservico.clienteservice.application.usecase.CriarCliente;
import com.microservico.clienteservice.application.usecase.AtualizarCliente;
import com.microservico.clienteservice.application.usecase.BuscarCliente;
import com.microservico.clienteservice.domain.model.Cliente;
import com.microservico.clienteservice.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements CriarCliente, AtualizarCliente, BuscarCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente criarCliente(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setDataNascimento(cliente.getDataNascimento());
        clienteExistente.setEndereco(cliente.getEndereco());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> porCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    // Novo método para listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}
