package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoUnidadeEnderecoResponseDto;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase {
    RespostaPaginada<ServidorEfetivoUnidadeEnderecoResponseDto> execute(String parteNome, int pagina, int tamanho);

}
