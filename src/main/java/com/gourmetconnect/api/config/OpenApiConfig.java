package com.gourmetconnect.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GourmetConnect API")
                        .version("1.0.0")
                        .description("Backend de red social gastronómica con soporte de geolocalización basada en MongoDB."));
    }


}