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
        // Forzamos a Swagger a reconocer el protocolo HTTPS del túnel público
        Server ngrokServer = new Server()
                .url("https://unloving-panhandle-winner.ngrok-free.dev")
                .description("Servidor Remoto Seguro (ngrok)");

        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor Local de Desarrollo");

        return new OpenAPI()
                .info(new Info()
                        .title("GourmetConnect API")
                        .version("1.0.0")
                        .description("Backend de red social gastronómica con soporte de geolocalización basada en MongoDB."))
                .servers(List.of(ngrokServer, localServer)); // Inyectamos ambos servidores
    }

    /*@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GourmetConnect API")
                        .version("1.0.0")
                        .description("Backend de red social gastronómica con soporte de geolocalización basada en MongoDB."));
    }*/

}