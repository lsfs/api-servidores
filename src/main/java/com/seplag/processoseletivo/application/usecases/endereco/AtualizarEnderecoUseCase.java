package com.seplag.processoseletivo.application.usecases.endereco;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;

public interface AtualizarEnderecoUseCase {
    EnderecoResponseDto execute(Long id, EnderecoRequestDto enderecoRequestDto);
}
