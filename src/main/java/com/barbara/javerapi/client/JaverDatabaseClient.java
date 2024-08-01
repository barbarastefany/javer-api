package com.barbara.javerapi.client;

import com.barbara.javerapi.controller.dto.AtualizarClienteDto;
import com.barbara.javerapi.controller.dto.ClienteDto;
import com.barbara.javerapi.controller.dto.CriarClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "javer-database", url = "http://localhost:8080")
public interface JaverDatabaseClient {

    @PostMapping("/cliente")
    ClienteDto criarCliente(@RequestBody CriarClienteDto criarClienteDto);

    @GetMapping("/cliente")
    List<ClienteDto> listarClientes();

    @GetMapping("/cliente/{id}")
    ClienteDto buscarClientePorId(@PathVariable Long id);

    @GetMapping("/cliente/{id}/score-credito")
    Float calculoScoreCredito(@PathVariable Long id);

    @PutMapping("/cliente/{id}")
    AtualizarClienteDto atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDto atualizarClienteDto);

    @DeleteMapping("/cliente/{id}")
    void deletarCliente(@PathVariable Long id);
}
