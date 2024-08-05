package com.barbara.javerapi.controller;

import com.barbara.javerapi.client.JaverDatabaseClient;
import com.barbara.javerapi.controller.dto.AtualizarClienteDto;
import com.barbara.javerapi.controller.dto.ClienteDto;
import com.barbara.javerapi.controller.dto.CriarClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import feign.FeignException;

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
    @Operation(summary = "Criar", description = "Método que cria um cliente.", tags = "Cliente")
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
    @Operation(summary = "Listar", description = "Método que retorna uma lista de clientes.", tags = "Cliente")
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteDto> clientes = javerDatabaseClient.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Busca um cliente por ID
    @Operation(summary = "Retornar", description = "Método que retorna um cliente pelo ID.", tags = "Cliente")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
            return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base de dados.");
        }
    }

    // Atualiza um cliente
    @Operation(summary = "Atualizar", description = "Método que atualiza um cliente.", tags = "Cliente")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id,
                                                                @RequestBody @Valid AtualizarClienteDto atualizarClienteDto) {
        try {
            ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
            AtualizarClienteDto clienteAtualizado = javerDatabaseClient.atualizarCliente(id, atualizarClienteDto);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base de dados.");
        }
    }

    // Exclui um cliente por ID
    @Operation(summary = "Deletar", description = "Método que deleta um cliente.", tags = "Cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
            javerDatabaseClient.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base de dados.");
        }
    }

    // Calcula o score de crédito a partir do saldo da conta corrente (scoreCredito = saldoCc * 0.1)
    @Operation(summary = "Calcular", description = "Método que calcula o score de crédito de um cliente considerando o valor do saldo da conta corrente multiplicado por 0.1.", tags = "Cálculo de score de crédito")
    @GetMapping("/{id}/score-credito")
    public ResponseEntity<String> calculoScoreCredito(@PathVariable Long id) {

        try {
            ClienteDto cliente = javerDatabaseClient.buscarClientePorId(id);
            if (cliente == null) {
                return ResponseEntity.notFound().build();
            }
            Float saldoCc = cliente.getSaldoCc();
            if(saldoCc == null) {
                return ResponseEntity.badRequest().build();
            }
            Float scoreCredito = saldoCc * 0.1F;
            return ResponseEntity.ok("Score de crédito: " + scoreCredito);
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base de dados.");
        }
    }
}