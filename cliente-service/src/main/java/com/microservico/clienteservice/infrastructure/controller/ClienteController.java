package com.microservico.clienteservice.infrastructure.controller;

import com.microservico.clienteservice.application.service.ClienteService;
import com.microservico.clienteservice.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        System.out.println("✅ ETAPA 01 - Cliente cadastrado com sucesso: " + clienteCriado.getNome());
        return clienteCriado;
    }


    // Mantém busca por ID (opcional)
    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable String id) {
        return clienteService.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Novo endpoint para buscar cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Void> verificarClientePorCpf(@PathVariable String cpf) {
        boolean existe = clienteService.clienteExistePorCpf(cpf);
        if (existe) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    @PutMapping("/id/{id}")
    public Cliente atualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }
}
