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
    private String telefone;
    private Float saldoCc;
}
