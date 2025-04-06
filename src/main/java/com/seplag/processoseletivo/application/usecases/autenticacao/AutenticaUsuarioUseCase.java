package com.seplag.processoseletivo.application.usecases.autenticacao;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginRequestDto;
import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;

public interface AutenticaUsuarioUseCase {
    LoginResponseDto execute(LoginRequestDto loginRequestDto);
}
