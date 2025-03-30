package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarUnidadesUseCase {

    RespostaPaginada<UnidadeResponseDto> execute(int pagina, int tamanho);

}
