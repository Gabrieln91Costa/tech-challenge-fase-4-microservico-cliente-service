package com.microservico.clienteservice.application.usecase;

import com.microservico.clienteservice.domain.model.Cliente;

public interface CriarCliente {
    Cliente criarCliente(Cliente cliente);
}
