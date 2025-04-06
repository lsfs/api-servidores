package com.seplag.processoseletivo.application.usecases.autenticacao.impl;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginRequestDto;
import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;
import com.seplag.processoseletivo.application.usecases.autenticacao.AutenticaUsuarioUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.GeraRefreshTokenUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.GeraTokenUseCase;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.domain.repositories.UsuarioRepository;
import com.seplag.processoseletivo.shared.exceptions.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticaUsuarioUseCaseImpl implements AutenticaUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeraTokenUseCase geraTokenUseCase;
    private final GeraRefreshTokenUseCase geraRefreshTokenUseCase;

    public AutenticaUsuarioUseCaseImpl(UsuarioRepository usuarioRepository,
                                       PasswordEncoder passwordEncoder,
                                       GeraTokenUseCase geraTokenUseCase,
                                       GeraRefreshTokenUseCase geraRefreshTokenUseCase) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.geraTokenUseCase = geraTokenUseCase;
        this.geraRefreshTokenUseCase = geraRefreshTokenUseCase;
    }



    @Override
    public LoginResponseDto execute(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.email();
        String senha = loginRequestDto.senha();

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new AuthenticationException("Informações de autenticação inválidas");
        }

        return new LoginResponseDto(
                geraTokenUseCase.execute(usuario),
                geraRefreshTokenUseCase.execute(usuario)
        );
    }
}
