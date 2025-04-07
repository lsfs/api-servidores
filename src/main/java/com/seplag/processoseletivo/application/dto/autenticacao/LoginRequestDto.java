package com.seplag.processoseletivo.application.dto.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para requisição de login")
public record LoginRequestDto(
        @Schema(description = "Email do usuário", example = "usuario@exemplo.com", required = true)
        String email,

        @Schema(description = "Senha do usuário", example = "senha123", required = true)
        String senha
) {
}