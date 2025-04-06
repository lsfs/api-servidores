package com.seplag.processoseletivo.application.usecases.autenticacao.impl;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;
import com.seplag.processoseletivo.application.usecases.autenticacao.GeraRefreshTokenUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.GeraTokenUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.RefreshTokenUseCase;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.domain.repositories.UsuarioRepository;
import com.seplag.processoseletivo.infra.security.JwtTokenProvider;
import com.seplag.processoseletivo.shared.exceptions.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioRepository usuarioRepository;
    private final GeraTokenUseCase geraTokenUseCase;
    private final GeraRefreshTokenUseCase geraRefreshTokenUseCase;

    public RefreshTokenUseCaseImpl(JwtTokenProvider jwtTokenProvider, UsuarioRepository usuarioRepository, GeraTokenUseCase geraTokenUseCase, GeraRefreshTokenUseCase geraRefreshTokenUseCase) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioRepository = usuarioRepository;
        this.geraTokenUseCase = geraTokenUseCase;
        this.geraRefreshTokenUseCase = geraRefreshTokenUseCase;
    }

    @Override
    public LoginResponseDto execute(String refreshToken) {

        if (!jwtTokenProvider.validaToken(refreshToken, "refresh")) {
            throw new AuthenticationException("Token de refresh inválido.");
        }

        String usuarioId = jwtTokenProvider.getUsuarioIdfromToken(refreshToken);

        Usuario usuario = usuarioRepository.findByEmail(usuarioId);

        return new LoginResponseDto(
                geraTokenUseCase.execute(usuario),
                geraRefreshTokenUseCase.execute(usuario)
        );
    }
}
