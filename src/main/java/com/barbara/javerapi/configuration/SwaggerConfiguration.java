package com.barbara.javerapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI().info(new Info().title("Javer API").version("1.0.0").description("Para testar as funcionalidades listadas é necessário estar com a aplicação javer-database em execução."));

    }
}
