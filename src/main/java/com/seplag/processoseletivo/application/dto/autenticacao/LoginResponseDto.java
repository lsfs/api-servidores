package com.seplag.processoseletivo.application.dto.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta de login")
public record LoginResponseDto(
        @Schema(description = "Token de acesso", example = "eyJhbGci...")
        String token,

        @Schema(description = "Token de refresh", example = "eyJhbGci...")
        String refreshToken
) {
}