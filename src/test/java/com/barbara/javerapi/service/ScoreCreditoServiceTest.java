package com.barbara.javerapi.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScoreCreditoServiceTest {

    @Mock
    private ScoreCreditoService scoreCreditoService;

    @InjectMocks
    private ScoreCreditoService scoreCreditoServiceMock;

    // GET Score de Crédito
    @Nested
    class DeveCalcularScoreCredito {

        @Test
        @DisplayName("Deve calcular o score de crédito com base no saldo da conta corrente")
        void deveCalcularScoreCredito() {
            // Arrange
            Float saldoCc = 1000F;
            Float scoreEsperado = saldoCc * 0.1F;

            ScoreCreditoService scoreCreditoService = new ScoreCreditoService();

            // Act
            Float score = scoreCreditoService.calculoScoreCredito(saldoCc);

            // Assert
            assertEquals(scoreEsperado, score);

        }
    }
}