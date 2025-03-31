package com.seplag.processoseletivo.application.usecases.servidorefetivo;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface ListarPorUnidadeUseCase {
    RespostaPaginada<ServidorEfetivoDetailsResponseDto>  execute(Long unid_id, int pagina, int tamanho);
}
