package com.seplag.processoseletivo.application.usecases.endereco;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarEnderecosUseCase {

    RespostaPaginada<EnderecoResponseDto> execute(int pagina, int tamanho);
}
