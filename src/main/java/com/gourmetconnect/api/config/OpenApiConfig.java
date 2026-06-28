package com.gourmetconnect.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 1. Servidor de Producción en la Nube (Azure VM)
        Server azureServer = new Server()
                .url("http://172.182.209.253:8080")
                .description("Servidor de Producción (Azure VM)");

        // 2. Servidor de Desarrollo Local (IntelliJ / Windows)
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Entorno Local de Desarrollo");

        // Retornamos la configuración unificada de OpenAPI
        return new OpenAPI()
                .info(new Info()
                        .title("GourmetConnect API")
                        .version("1.0.0")
                        .description("Backend de red social gastronómica con soporte de geolocalización basada en MongoDB."))
                .servers(List.of(azureServer, localServer));
    }
}