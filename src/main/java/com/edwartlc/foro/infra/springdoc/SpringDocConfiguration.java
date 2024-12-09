package com.edwartlc.foro.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Foro")
                        .description("API Rest de aplicación web" +
                                "que simula el foro de una plataforma" +
                                "de educación virtual, con funcionalidades " +
                                "CRUD para gestión de temas.")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("foro@correo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://foro.com/api/licencia")));
    }
}
