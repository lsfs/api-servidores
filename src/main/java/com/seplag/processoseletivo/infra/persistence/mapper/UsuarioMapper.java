package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.infra.persistence.entity.UsuarioEntity;

public class UsuarioMapper {

    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha()
        );
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuario.getId());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setSenha(usuario.getSenha());

        return usuarioEntity;
    }
}
