package com.seplag.processoseletivo.application.usecases.autenticacao;

import com.seplag.processoseletivo.domain.model.Usuario;

public interface GeraTokenUseCase {
    String execute(Usuario usuario);
}
