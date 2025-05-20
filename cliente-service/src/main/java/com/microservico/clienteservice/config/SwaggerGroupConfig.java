package com.microservico.clienteservice.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerGroupConfig {

    @Bean
    public GroupedOpenApi clientesApi() {
        return GroupedOpenApi.builder()
                .group("clientes")  // Nome do grupo exibido no Swagger UI
                .pathsToMatch("/clientes/**")  // Endpoints que serão documentados
                .build();
    }

    // Se houver endpoints públicos (como cadastro), você pode configurar a liberação da segurança aqui
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/public/**")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    // Exemplo: liberar autenticação para o endpoint de cadastro
                    if (operation.getOperationId() != null && operation.getOperationId().equals("register")) {
                        operation.addSecurityItem(new SecurityRequirement()); // Remove exigência de auth
                    }
                    return operation;
                })
                .build();
    }
}
