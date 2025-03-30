package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

public interface BuscarUnidadePorIdUseCase {
    UnidadeResponseDto execute(Long id);
}
