package com.seplag.processoseletivo.application.dto.cidade;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para requisição de cidade")
public record CidadeRequestDto(
        @Schema(description = "Nome da cidade", example = "Cuiabá", required = true)
        String cid_nome,

        @Schema(description = "UF da cidade", example = "MT", required = true)
        String cid_uf
) {
    public CidadeRequestDto {
        if (cid_nome == null || cid_nome.isBlank()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser nulo ou vazio.");
        }
        if (cid_uf == null || cid_uf.isBlank()) {
            throw new IllegalArgumentException("A UF da cidade não pode ser nula ou vazia.");
        }
        if (cid_uf.length() != 2) {
            throw new IllegalArgumentException("A UF da cidade deve ter exatamente 2 caracteres.");
        }
    }
}