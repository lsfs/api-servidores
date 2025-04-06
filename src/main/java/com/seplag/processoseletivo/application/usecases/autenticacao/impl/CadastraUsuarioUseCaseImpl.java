package com.seplag.processoseletivo.application.usecases.autenticacao.impl;

import com.seplag.processoseletivo.application.dto.autenticacao.UsuarioRequestDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.autenticacao.CadastraUsuarioUseCase;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.domain.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CadastraUsuarioUseCaseImpl implements CadastraUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public CadastraUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public MensagemRetorno execute(UsuarioRequestDto usuarioRequestDto) {

        String senhaCriptografada = passwordEncoder.encode(usuarioRequestDto.senha());
        Usuario usuario = new Usuario(
                usuarioRequestDto.email(),
                senhaCriptografada
        );

        usuarioRepository.criar(usuario);
        return new MensagemRetorno("Usuário cadastrado com sucesso");

    }
}
