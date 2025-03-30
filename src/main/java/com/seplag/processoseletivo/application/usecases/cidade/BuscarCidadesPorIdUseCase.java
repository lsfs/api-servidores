package com.seplag.processoseletivo.application.usecases.cidade;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;

public interface BuscarCidadesPorIdUseCase {
    CidadeResponseDto execute(Long id);
}
