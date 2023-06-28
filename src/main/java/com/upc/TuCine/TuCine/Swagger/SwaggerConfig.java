package com.upc.TuCine.TuCine.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().addServersItem(new Server().url("http://backend-tucine-production.up.railway.app"));
    }
}