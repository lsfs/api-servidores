package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;

public interface AtualizarServidorEfetivoUseCase {
    ServidorEfetivoResponseDto execute(Long id, ServidorEfetivoRequestDto servidorEfetivoRequestDto);
}
