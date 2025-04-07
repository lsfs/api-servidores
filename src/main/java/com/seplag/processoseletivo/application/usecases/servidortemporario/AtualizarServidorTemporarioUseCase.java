package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTemporarioRequestUpdateDto;

public interface AtualizarServidorTemporarioUseCase {

    ServidorTempResponseDto execute(Long id, ServidorTemporarioRequestUpdateDto servidorTempRequestDto);

}
