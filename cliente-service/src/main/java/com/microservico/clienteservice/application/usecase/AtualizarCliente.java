package com.microservico.clienteservice.application.usecase;

import com.microservico.clienteservice.domain.model.Cliente;

public interface AtualizarCliente {
    Cliente atualizarCliente(Long id, Cliente cliente);
}
