package com.seplag.processoseletivo.application.usecases.autenticacao;

import com.seplag.processoseletivo.application.dto.autenticacao.UsuarioRequestDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.domain.model.Usuario;
import com.seplag.processoseletivo.infra.persistence.entity.UsuarioEntity;

public interface CadastraUsuarioUseCase {
    MensagemRetorno execute(UsuarioRequestDto usuarioRequestDto);
}
