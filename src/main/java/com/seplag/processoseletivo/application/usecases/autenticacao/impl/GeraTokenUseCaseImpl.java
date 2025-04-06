package com.seplag.processoseletivo.application.usecases.autenticacao.impl;

import com.seplag.processoseletivo.application.usecases.autenticacao.GeraTokenUseCase;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.infra.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class GeraTokenUseCaseImpl implements GeraTokenUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    public GeraTokenUseCaseImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String execute(Usuario usuario) {
        return this.jwtTokenProvider.geraTokenDeAcesso(usuario);
    }
}
