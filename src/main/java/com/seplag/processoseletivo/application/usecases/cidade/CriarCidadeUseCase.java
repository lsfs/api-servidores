package com.seplag.processoseletivo.application.usecases.cidade;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;

public interface CriarCidadeUseCase {

    CidadeResponseDto execute(CidadeRequestDto cidadeRequestDto);

}
