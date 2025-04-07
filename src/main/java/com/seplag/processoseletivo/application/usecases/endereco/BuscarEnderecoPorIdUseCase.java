package com.seplag.processoseletivo.application.usecases.endereco;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;

public interface BuscarEnderecoPorIdUseCase {
    EnderecoResponseDto execute(Long id);
}
