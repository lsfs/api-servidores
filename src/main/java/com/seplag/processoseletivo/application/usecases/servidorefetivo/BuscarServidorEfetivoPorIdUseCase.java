package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;

public interface BuscarServidorEfetivoPorIdUseCase {
    ServidorEfetivoResponseDto execute(Long id);
}
