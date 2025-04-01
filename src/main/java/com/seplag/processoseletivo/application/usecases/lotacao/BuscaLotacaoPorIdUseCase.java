package com.seplag.processoseletivo.application.usecases.lotacao;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;

public interface BuscaLotacaoPorIdUseCase {

    LotacaoResponseDto execute(Long id);
}
