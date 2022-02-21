package com.taulia.supplier.onboarding.common.openapi

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition
class OpenApiConfiguration {

    @Bean
    OpenAPI customOpenAPI(@Value("\${application-description:Supplier On-boarding bff API}") String appDescription,
                          @Value("\${application-version:1}") String appVersion) {

        var contact = new Contact()
        contact.setEmail("andrei.tataru@taulia.com")
        contact.setName("Tataru Andrei-Emanuel")

        return new OpenAPI()
                .info(new Info()
                        .title("Supplier On-boarding API")
                        .contact(contact)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
    }
}
