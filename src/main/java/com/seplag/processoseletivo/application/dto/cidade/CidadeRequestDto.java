package com.seplag.processoseletivo.application.dto.cidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de cidade")
public record CidadeRequestDto(
        @Schema(description = "Nome da cidade", example = "Cuiabá", required = true)
        @NotBlank(message = "O nome da cidade não pode ser nulo ou vazio.")
        String cid_nome,

        @Schema(description = "UF da cidade", example = "MT", required = true)
        @NotBlank(message = "A UF da cidade não pode ser nula ou vazia.")
        String cid_uf
) {
}