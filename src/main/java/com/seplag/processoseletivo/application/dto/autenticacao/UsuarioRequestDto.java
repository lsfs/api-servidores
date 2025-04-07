package com.seplag.processoseletivo.application.dto.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de usuário")
public record UsuarioRequestDto(
        @Schema(description = "Email do usuário", example = "usuario@exemplo.com", required = true)
        @NotBlank(message = "O email não pode ser nulo ou vazio.")
        @Email(message = "O email deve ser válido.")
        String email,

        @Schema(description = "Senha do usuário", example = "senha123", required = true)
        @NotBlank(message = "A senha não pode ser nula ou vazia.")
        String senha
) {
}