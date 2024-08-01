package com.barbara.javerapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Long clienteId;

    private String nome;
    private Long telefone;
    private Float saldoCc;

    public ClienteDto(String nome, Long telefone, Float saldoCc) {
    }
}
