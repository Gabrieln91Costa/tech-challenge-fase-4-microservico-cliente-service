package com.microservico.clienteservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // Definindo as informações gerais da API
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Clientes")
                        .description("API para cadastro, consulta e atualização de clientes")
                        .version("1.0"));

        // Adicionando a segurança JWT
        openAPI.addSecurityItem(new SecurityRequirement().addList("jwt_auth"))
                .schemaRequirement("jwt_auth", createSecurityScheme());

        return openAPI;
    }

    // Definindo o esquema de segurança JWT (bearer)
    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name("jwt_auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}