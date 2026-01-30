package com.ai.codeinspector.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI codeInspectorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Code Inspector API")
                        .description("APIs for inspecting code quality and reports")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Juned Shaikh")
                                .email("junedshaikh3161@gmail.com")
                                .url("https://github.com/Juned1306"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                );
    }
}
