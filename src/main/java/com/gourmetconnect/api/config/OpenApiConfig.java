package com.gourmetconnect.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 1. Servidor de Producción en Render
        Server renderServer = new Server()
                .url("https://gourmetconnect-api.onrender.com")
                .description("Servidor de Producción (Render)");

        // 2. Servidor de Desarrollo Local (IntelliJ / Windows)
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Entorno Local de Desarrollo");

        // Retornamos la configuración inyectando las variables correctas
        return new OpenAPI()
                .info(new Info()
                        .title("GourmetConnect API")
                        .version("1.0.0")
                        .description("Backend de red social gastronómica con soporte de geolocalización basada en MongoDB."))
                .servers(List.of(renderServer, localServer)); // <-- REVISADO: Pasamos renderServer y localServer
    }
}