package com.seplag.processoseletivo.application.usecases.cidade;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarCidadesUseCase {

    RespostaPaginada<CidadeResponseDto> execute(int pagina, int tamanho);

}
