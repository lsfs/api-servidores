package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;

public interface DeletarServidorEfetivoUseCase {
    MensagemRetorno execute(Long id);
}
