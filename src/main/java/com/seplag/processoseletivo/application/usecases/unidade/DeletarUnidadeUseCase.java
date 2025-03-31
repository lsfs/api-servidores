package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;

public interface DeletarUnidadeUseCase {
    MensagemRetorno execute(Long idUnidade);
}
