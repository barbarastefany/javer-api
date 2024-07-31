package com.barbara.javerapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarClienteDto {

    private String nome;
    private Long telefone;
    private Float saldoCc;
}