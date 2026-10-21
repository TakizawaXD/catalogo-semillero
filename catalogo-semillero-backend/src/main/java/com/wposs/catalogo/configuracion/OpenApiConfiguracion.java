package com.wposs.catalogo.configuracion;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguracion {

    @Bean
    OpenAPI catalogoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catálogo WPOSS API")
                        .version("v1")
                        .description("API REST para consultar y administrar el catálogo de productos"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
