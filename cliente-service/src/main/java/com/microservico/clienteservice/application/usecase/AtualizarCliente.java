package com.microservico.clienteservice.application.usecase;

import com.microservico.clienteservice.domain.model.Cliente;

public interface AtualizarCliente {

    Cliente atualizarCliente(String id, Cliente cliente); // Altere o tipo de ID para String
}
