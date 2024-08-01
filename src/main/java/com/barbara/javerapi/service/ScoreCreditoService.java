package com.barbara.javerapi.service;

import org.springframework.stereotype.Service;

@Service
public class ScoreCreditoService {

    public Float calculoScoreCredito(Float saldoCc) {

        return saldoCc * 0.1f;
    }
}
