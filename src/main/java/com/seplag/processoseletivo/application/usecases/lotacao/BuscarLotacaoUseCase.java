package com.seplag.processoseletivo.application.usecases.lotacao;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarLotacaoUseCase {

    RespostaPaginada<LotacaoResponseDto> execute(int pagina, int tamanho);

}
