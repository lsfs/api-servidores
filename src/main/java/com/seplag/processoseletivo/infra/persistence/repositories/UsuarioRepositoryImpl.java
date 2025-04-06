package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.domain.repositories.UsuarioRepository;
import com.seplag.processoseletivo.infra.persistence.entity.UsuarioEntity;
import com.seplag.processoseletivo.infra.persistence.mapper.UsuarioMapper;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.UsuarioJpaRepository;
import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(UsuarioMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Erro ao autenticar usuário"));
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(UsuarioMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Erro ao autenticar usuário"));
    }

    @Override
    public Usuario criar(Usuario usuario) {

        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(usuario);

       return UsuarioMapper.toDomain(usuarioJpaRepository.save(usuarioEntity));
    }
}
