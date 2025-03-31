package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;

public interface BuscarServidorTempPorIdUseCase {

    ServidorTempResponseDto execute(Long id);
}
