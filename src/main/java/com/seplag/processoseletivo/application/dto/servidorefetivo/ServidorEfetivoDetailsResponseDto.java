package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;


public record ServidorEfetivoDetailsResponseDto(
        String nome,
        int idade,
        UnidadeResponseDto unidadeLotacao
) {
    // You can add additional methods here if needed
}
