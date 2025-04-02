package com.truonghuunam.backend_test__online.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quiz API")
                        .version("1.0")
                        .description("API cho hệ thống thi trắc nghiệm nâng cao"))
                .servers(List.of(
                        new Server().url("https://quiz-server-production-eddc.up.railway.app")
                                .description("Production Server")));
    }
}
