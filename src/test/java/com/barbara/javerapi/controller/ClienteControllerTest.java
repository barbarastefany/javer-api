package com.barbara.javerapi.controller;

import com.barbara.javerapi.client.JaverDatabaseClient;
import com.barbara.javerapi.controller.dto.AtualizarClienteDto;
import com.barbara.javerapi.controller.dto.ClienteDto;
import com.barbara.javerapi.controller.dto.CriarClienteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private JaverDatabaseClient javerDatabaseClient;

    @InjectMocks
    private ClienteController clienteController;

    // POST
    @Nested
    class DeveCriarClienteComSucesso {

        @Test
        @DisplayName("Deve criar um cliente com sucesso")
        void deveCriarClienteComSucesso() {

            // Arrange
            var clienteDto = new ClienteDto(
                    "Teste",
                    40028922L,
                    true,
                    15000f
            );

            doReturn(clienteDto).when(javerDatabaseClient).criarCliente(any(CriarClienteDto.class));

            var input = new CriarClienteDto(
                    "Teste",
                    40028922L,
                    15000f
            );

            // Act
            ResponseEntity<ClienteDto> response = clienteController.criarCliente(input);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(clienteDto, response.getBody());
        }

        @Test
        @DisplayName("Não deve criar cliente com campos vazios")
        void naoDeveCriarClienteComCamposVazios() {


            // Arrange
            var input = new CriarClienteDto(
                    null,
                    null,
                    null);

            // Act
            ResponseEntity<ClienteDto> response = clienteController.criarCliente(input);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    // GET LIST
    @Nested
    class DeveRetornarUmaListaDeClientes {

        @Test
        @DisplayName("Deve retornar uma lista de clientes CASO existam clientes para serem retornados")
        void deveRetornarUmaListaDeClientes() {

            // Arrange
            List<ClienteDto> clientesEsperados = Arrays.asList(
                    new ClienteDto("Joãozinho", 123456789L, true, 2000F),
                    new ClienteDto("Pedrinho", 987654321L, true, 5000F)
            );

            when(javerDatabaseClient.listarClientes()).thenReturn(clientesEsperados);

            // Act
            ResponseEntity<List<ClienteDto>> response = clienteController.listarClientes();

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(clientesEsperados, response.getBody());

        }

        @Test
        @DisplayName("Deve retornar uma lista vazia caso não existam clientes para serem retornados")
        void deveRetornarListaVaziaCasoNaoExistamClientes() {

            // Arrange
            List<ClienteDto> clientesEsperados = Collections.emptyList();

            // Act
            ResponseEntity<List<ClienteDto>> response = clienteController.listarClientes();

            // Assert
            assertTrue(response.getBody().isEmpty());
        }
    }

    // GET BY ID
    @Nested
    class DeveRetornarUmClientePorId {

        @Test
        @DisplayName("Deve retornar um cliente por ID")
        void deveRetornarUmClientePorId() {

            // Arrange
            Long clienteId = 1L;
            ClienteDto clienteEsperado = new ClienteDto(
                    "Joãozinho",
                    12345678L,
                    true,
                    2000F
            );

            when(javerDatabaseClient.buscarClientePorId(clienteId)).thenReturn(clienteEsperado);

            // Act
            ResponseEntity<ClienteDto> response = (ResponseEntity<ClienteDto>) clienteController.buscarClientePorId(clienteId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(clienteEsperado, response.getBody());
        }

        @Test
        @DisplayName("Deve retornar uma mensagem de erro caso o cliente não exista")
        void deveRetornarErroCasoClienteNaoExista() {

            // Arrange
            Long clienteId = -2L;

            when(javerDatabaseClient.buscarClientePorId(clienteId)).thenReturn(null);

            // Act
            ResponseEntity<ClienteDto> response = (ResponseEntity<ClienteDto>) clienteController.buscarClientePorId(clienteId);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    // PUT
    @Nested
    class DeveAtualizarUmClienteComSucesso {

        @Test
        @DisplayName("Deve atualizar um cliente com sucesso")
        void deveAtualizarUmClienteComSucesso() {

            // Arrange
            Long clienteId = 1L;
            AtualizarClienteDto atualizarClienteDto = new AtualizarClienteDto(123456789L);
            AtualizarClienteDto clienteEsperado = new AtualizarClienteDto(45678936L);

            when(javerDatabaseClient.atualizarCliente(clienteId, atualizarClienteDto)).thenReturn(clienteEsperado);

            // Act
            ResponseEntity<AtualizarClienteDto> response = (ResponseEntity<AtualizarClienteDto>) clienteController.atualizarCliente(clienteId, atualizarClienteDto);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(clienteEsperado.getTelefone(), response.getBody().getTelefone());
        }
    }

    // DELETE
    @Nested
    class DeveDeletarUmClienteComSucesso {

        @Test
        @DisplayName("Deve deletar um cliente com sucesso")
        void deveDeletarUmClienteComSucesso() {

            // Arrange
            Long clienteId = 1L;

            // Act
            ResponseEntity<Void> response = (ResponseEntity<Void>) clienteController.deletarCliente(clienteId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    // Calcula o score de crédito
    @Nested
    class CalculaOScoreDeCredito {

        @Test
        @DisplayName("Deve retornar NOT FOUND caso o cliente não exista")
        void deveRetornarErroCasoClienteNaoExista() {

            // Arrange
            Long clienteId = 99L;

            when(javerDatabaseClient.buscarClientePorId(clienteId)).thenReturn(null);

            // Act
            ResponseEntity<String> response = clienteController.calculoScoreCredito(clienteId);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        @DisplayName("Deve retornar BAD REQUEST caso o saldo da conta corrente seja nulo")
        void deveRetornarBadRequestCasoSaldoSejaNulo() {

            // Arrange
            Long clienteId = 1L;
            ClienteDto clienteDto = new ClienteDto("Luana", 4567891L, true,null);

            when(javerDatabaseClient.buscarClientePorId(clienteId)).thenReturn(clienteDto);

            // Act
            ResponseEntity<String> response = clienteController.calculoScoreCredito(clienteId);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("Calcula o score de crédito com base no saldo da conta corrente")
        void calculaOScoreDeCredito() {

            // Arrange
            Long clienteId = 1L;
            Float saldoCc = 3500F;
            Float scoreEsperado = saldoCc * 0.1F;

            ClienteDto clienteDto = new ClienteDto(
                    clienteId,
                    "Bruno",
                    123456789L,
                    true,
                    saldoCc
            );

            when(javerDatabaseClient.buscarClientePorId(clienteId)).thenReturn(clienteDto);

            // Act
            ResponseEntity<String> response = clienteController.calculoScoreCredito(clienteId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Score de crédito: " + scoreEsperado, response.getBody());
        }
    }
}