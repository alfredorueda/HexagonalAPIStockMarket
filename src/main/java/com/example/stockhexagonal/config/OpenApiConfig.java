package com.example.stockhexagonal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI/Swagger documentation
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI stockApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Price Hexagonal API")
                        .description("REST API for retrieving stock prices using Hexagonal Architecture")
                        .version("v1.0.0")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}
