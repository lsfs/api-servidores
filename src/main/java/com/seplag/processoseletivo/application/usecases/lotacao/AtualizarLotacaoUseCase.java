package com.seplag.processoseletivo.application.usecases.lotacao;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;

public interface AtualizarLotacaoUseCase {
    LotacaoResponseDto execute(Long id, LotacaoRequestDto lotacaoRequestDto);
}
