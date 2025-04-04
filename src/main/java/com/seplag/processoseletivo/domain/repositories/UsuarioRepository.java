package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario findByEmail(String email);
    Usuario findById(Long id);

}
