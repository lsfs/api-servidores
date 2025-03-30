package com.seplag.processoseletivo.application.usecases.endereco;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;

public interface CriarEnderecoUseCase {

    EnderecoResponseDto execute(EnderecoRequestDto dto);

}
