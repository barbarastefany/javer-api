package com.barbara.javerapi.controller;

import com.barbara.javerapi.client.JaverDatabaseClient;
import com.barbara.javerapi.controller.dto.AtualizarClienteDto;
import com.barbara.javerapi.controller.dto.ClienteDto;
import com.barbara.javerapi.controller.dto.CriarClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private JaverDatabaseClient javerDatabaseClient;

    @Autowired
    public ClienteController(JaverDatabaseClient javerDatabaseClient) {
        this.javerDatabaseClient = javerDatabaseClient;
    }

    // Criar um cliente
    @PostMapping
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody CriarClienteDto criarClienteDto) {
        ClienteDto clienteCriado = javerDatabaseClient.criarCliente(criarClienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteDto> clientes = javerDatabaseClient.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Busca um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
        ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Atualiza um cliente
    @PutMapping("/{id}")
    public ResponseEntity<AtualizarClienteDto> atualizarCliente(@PathVariable Long id,
                                                                @RequestBody AtualizarClienteDto atualizarClienteDto) {
        AtualizarClienteDto clienteAtualizado = javerDatabaseClient.atualizarCliente(id, atualizarClienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Exclui um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        javerDatabaseClient.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}