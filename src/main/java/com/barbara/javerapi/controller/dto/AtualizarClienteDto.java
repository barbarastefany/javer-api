package com.barbara.javerapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarClienteDto {

    @NotBlank(message = "É obrigatório informar um número de telefone")
    private Long telefone;
}
