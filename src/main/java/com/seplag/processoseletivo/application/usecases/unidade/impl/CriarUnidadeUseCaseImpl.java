package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.CriaUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarUnidadeUseCaseImpl implements CriaUnidadeUseCase {

    private final UnidadeRepository unidadeRepository;

    public CriarUnidadeUseCaseImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public UnidadeResponseDto execute(UnidadeRequestDto unidadeRequestDto) {

        Unidade unidade = new Unidade();
        unidade.setUnid_nome(unidadeRequestDto.unid_nome());
        unidade.setUni_sigla(unidadeRequestDto.unid_sigla());

        Unidade unidadeCriada = unidadeRepository.criar(unidade);
        return UnidadeResponseDto.of(unidadeCriada);
    }


}
