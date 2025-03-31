package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;

public interface DeletarServidorTemporarioUseCase {
    MensagemRetorno execute(Long id);
}
