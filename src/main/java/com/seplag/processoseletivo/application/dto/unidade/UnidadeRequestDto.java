package com.seplag.processoseletivo.application.dto.unidade;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "DTO para solicitação de unidade")
public record UnidadeRequestDto(
        @Schema(description = "Nome da unidade", example = "Departamento Financeiro", required = true)
        String unid_nome,

        @Schema(description = "Sigla da unidade", example = "FIN", required = true)
        String unid_sigla,

        @Schema(description = "Endereços da unidade", required = true)
        Set<Long> enderecos
) {

    public UnidadeRequestDto {
        if (unid_nome == null || unid_nome.isBlank()) {
            throw new IllegalArgumentException("Nome da unidade não pode ser nulo ou vazio");
        }
        if (unid_sigla == null || unid_sigla.isBlank()) {
            throw new IllegalArgumentException("Sigla da unidade não pode ser nula ou vazia");
        }
        if (enderecos == null || enderecos.isEmpty()) {
            throw new IllegalArgumentException("Endereços não podem ser nulos ou vazios");
        }
    }

}
