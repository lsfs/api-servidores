package com.seplag.processoseletivo.application.dto.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de refresh token")
public record RefreshTokenRequestDto(
        @Schema(description = "Token de refresh", example = "eyJhbGci...")
        @NotBlank(message = "O token não pode ser nulo ou vazio.")
        String token
) {
}
