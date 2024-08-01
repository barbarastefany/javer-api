package com.barbara.javerapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarClienteDto {

    @NotBlank(message = "É obrigatório informar um nome")
    private String nome;

    @NotNull(message = "É obrigatório informar um número de telefone")
    private Long telefone;

    @NotNull(message = "É obrigatório informar um saldo inicial")
    private Float saldoCc;
}