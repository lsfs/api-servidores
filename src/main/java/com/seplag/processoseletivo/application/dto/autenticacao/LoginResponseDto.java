package com.seplag.processoseletivo.application.dto.autenticacao;

public record LoginResponseDto(
        String token,
        String refreshToken
) {
}
