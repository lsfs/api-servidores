package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.domain.repositories.UsuarioRepository;
import com.seplag.processoseletivo.infra.persistence.entity.UsuarioEntity;
import com.seplag.processoseletivo.infra.persistence.mapper.UsuarioMapper;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.UsuarioJpaRepository;
import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(UsuarioMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Usuário inexistente"));
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(UsuarioMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Usuário inexistente"));
    }

    @Override
    public Usuario criar(Usuario usuario) {

        if (existeEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);

       return UsuarioMapper.toDomain(usuarioJpaRepository.save(usuarioEntity));
    }

    private boolean existeEmail(String email) {
        return usuarioJpaRepository.existsByEmail(email);
    }

}
