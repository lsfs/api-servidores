package com.seplag.processoseletivo.application.usecases.unidade;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

public interface AtualizarUnidadeUseCase {
    UnidadeResponseDto execute(Long unid_id, UnidadeRequestDto unidadeRequestDto);
}
