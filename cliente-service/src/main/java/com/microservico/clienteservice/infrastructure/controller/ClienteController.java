package com.microservico.clienteservice.infrastructure.controller;

import com.microservico.clienteservice.application.service.ClienteService;
import com.microservico.clienteservice.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    // Alteração no tipo do @PathVariable de Long para String
    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable String id) {  // Agora o id é String
        return clienteService.porId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    // Alteração no tipo do @PathVariable de Long para String
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {  // Agora o id é String
        return clienteService.atualizarCliente(id, cliente); // Passando o id como String
    }
}
