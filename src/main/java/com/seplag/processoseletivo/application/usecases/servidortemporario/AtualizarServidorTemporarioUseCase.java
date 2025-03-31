package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;

public interface AtualizarServidorTemporarioUseCase {

    ServidorTempResponseDto execute(Long id, ServidorTempRequestDto servidorTempRequestDto);

}
