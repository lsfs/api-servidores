package com.seplag.processoseletivo.application.usecases.autenticacao.impl;

import com.seplag.processoseletivo.application.usecases.autenticacao.GeraRefreshTokenUseCase;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.infra.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class GeraRefreshTokenUseCaseImpl implements GeraRefreshTokenUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    public GeraRefreshTokenUseCaseImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String execute(Usuario usuario) {
        return this.jwtTokenProvider.geraRefreshToken(usuario);
    }
}
