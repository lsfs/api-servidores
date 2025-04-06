package com.seplag.processoseletivo.application.dto.autenticacao;

public record LoginRequestDto(
        String email,
        String senha
) {
}
