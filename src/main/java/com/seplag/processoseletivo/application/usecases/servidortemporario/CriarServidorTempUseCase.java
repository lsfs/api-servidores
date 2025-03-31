package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;

public interface CriarServidorTempUseCase {
    ServidorTempResponseDto execute(ServidorTempRequestDto servidorTempRequestDto);
}
