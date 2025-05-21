package com.microservico.clienteservice.domain.repository;

import com.microservico.clienteservice.domain.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> { // ID como String

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findById(String id);  // ID como String
}
