package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

public interface CriaUnidadeUseCase {

    UnidadeResponseDto execute(UnidadeRequestDto unidadeRequestDto);

}
