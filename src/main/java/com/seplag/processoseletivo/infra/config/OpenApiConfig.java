package com.seplag.processoseletivo.infra.config;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Api - Processo Seletivo SEPLAG")
                        .description("Documentacao da API do Processo Seletivo SEPLAG"))
                .addTagsItem(new Tag().name("Admin").description("Teste de permissão de acesso"))
                .addTagsItem(new Tag().name("Autenticação").description("Endpoints de login, registro e refresh"))
                .addTagsItem(new Tag().name("Cidade").description("Operações relacionadas a cidades"))
                .addTagsItem(new Tag().name("Endereço").description("Operações relacionadas a endereços"))
                .addTagsItem(new Tag().name("FotoPessoa").description("Operações relacionadas a fotos de pessoas"))
                .addTagsItem(new Tag().name("Lotação").description("Operações relacionadas a lotações"))
                .addTagsItem(new Tag().name("ServidorEfetivo").description("Operações relacionadas a servidores efetivos"))
                .addTagsItem(new Tag().name("ServidorTemporário").description("Operações relacionadas a servidores temporários"))
                .addTagsItem(new Tag().name("Unidade").description("Operações relacionadas a unidades"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

}
