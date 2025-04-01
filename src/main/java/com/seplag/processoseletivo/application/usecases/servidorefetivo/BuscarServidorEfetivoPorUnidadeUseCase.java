package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarServidorEfetivoPorUnidadeUseCase {
    RespostaPaginada<ServidorEfetivoDetailsResponseDto> execute(Long unidadeId, int pagina, int tamanho);
}
