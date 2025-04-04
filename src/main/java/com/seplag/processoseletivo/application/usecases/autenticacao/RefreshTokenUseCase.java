package com.seplag.processoseletivo.application.usecases.autenticacao;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;

public interface RefreshTokenUseCase {
    LoginResponseDto execute(String refreshToken);

}
