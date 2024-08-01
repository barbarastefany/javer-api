package com.barbara.javerapi.controller;

import com.barbara.javerapi.client.JaverDatabaseClient;
import com.barbara.javerapi.controller.dto.AtualizarClienteDto;
import com.barbara.javerapi.controller.dto.ClienteDto;
import com.barbara.javerapi.controller.dto.CriarClienteDto;
import jakarta.validation.Valid;
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
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody @Valid CriarClienteDto criarClienteDto) {
        if(criarClienteDto.getNome() == null || criarClienteDto.getNome().isEmpty() ||
                criarClienteDto.getTelefone() == null || criarClienteDto.getTelefone() == 0 ||
                (criarClienteDto.getSaldoCc() == null || criarClienteDto.getSaldoCc() == 0.0f)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

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
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();}

    // Atualiza um cliente
    @PutMapping("/{id}")
    public ResponseEntity<AtualizarClienteDto> atualizarCliente(@PathVariable Long id,
                                                                @RequestBody @Valid AtualizarClienteDto atualizarClienteDto) {
        AtualizarClienteDto clienteAtualizado = javerDatabaseClient.atualizarCliente(id, atualizarClienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Exclui um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        javerDatabaseClient.deletarCliente(id);
        return ResponseEntity.ok().build();
    }

    // Calcula o score de crédito a partir do saldo da conta corrente (scoreCredito = saldoCc * 0.1)
    @GetMapping("/{id}/score-credito")
    public ResponseEntity<String> calculoScoreCredito(@PathVariable Long id) {

        ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        Float saldoCc = cliente.getSaldoCc();
        if (saldoCc == null) {
            return ResponseEntity.badRequest().build();
        }
        Float scoreCredito = saldoCc * 0.1F;
        return ResponseEntity.ok("Score de crédito: " + scoreCredito);
    }
}