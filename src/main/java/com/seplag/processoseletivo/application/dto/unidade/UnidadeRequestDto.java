package com.seplag.processoseletivo.application.dto.unidade;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Schema(description = "DTO para solicitação de unidade")
public record UnidadeRequestDto(
        @Schema(description = "Nome da unidade", example = "Departamento Financeiro", required = true)
        @NotBlank(message = "O nome da unidade não pode ser nulo ou vazio.")
        String unid_nome,

        @Schema(description = "Sigla da unidade", example = "FIN", required = true)
        @NotBlank(message = "A sigla da unidade não pode ser nula ou vazia.")
        String unid_sigla,

        @Schema(description = "Endereços da unidade", required = true)
        @NotEmpty(message = "Os endereços da unidade não podem ser nulos.")
        Set<Long> enderecos
) {
}
