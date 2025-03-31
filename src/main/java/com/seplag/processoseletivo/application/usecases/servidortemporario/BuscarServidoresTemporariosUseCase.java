package com.seplag.processoseletivo.application.usecases.servidortemporario;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarServidoresTemporariosUseCase {
    RespostaPaginada<ServidorTempResponseDto> execute(int pagina,  int tamanho);
}
