package com.barbara.javerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JaverApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaverApiApplication.class, args);
    }

}
