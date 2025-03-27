package com.truonghuunam.backend_test__online.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI simpleOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quiz Online API")
                        .version("1.0")
                        .description("API docs for Quiz System"));
    }
}