package com.microservico.clienteservice.application.usecase;

import com.microservico.clienteservice.domain.model.Cliente;

import java.util.Optional;

public interface BuscarCliente {
    Optional<Cliente> porId(Long id);
    Optional<Cliente> porCpf(String cpf);
}
