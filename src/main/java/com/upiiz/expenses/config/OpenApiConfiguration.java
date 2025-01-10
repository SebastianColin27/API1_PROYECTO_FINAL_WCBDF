package com.upiiz.expenses.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Employees",
                description = "Esta API proporciona acceso a la api de employees",
                version = "1.0.0",
                contact = @Contact(
                        name = "José Sebstián Colin Becerra"

                ),
                license = @License(),
                termsOfService = "En proceso"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Servidor de Produccion",
                        url = "https://api-proyecto-final-wcbdf.onrender.com"
                )
        },
        tags = {
                @Tag(
                        name = "Employees",
                        description = "Endpoints para employees"
                )
        }
)
public class OpenApiConfiguration {

}
