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
    private Boolean correntista;
    private Float saldoCc;

    public ClienteDto(String nome, Long telefone, Boolean correntista, Float saldoCc) {
        this.nome = nome;
        this.telefone = telefone;
        this.correntista = true;
        this.saldoCc = saldoCc;
    }
}
