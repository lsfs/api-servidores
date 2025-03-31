package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarServidoresEfetivosUseCase {
    RespostaPaginada<ServidorEfetivoResponseDto> execute(int pagina, int tamanho);
}
