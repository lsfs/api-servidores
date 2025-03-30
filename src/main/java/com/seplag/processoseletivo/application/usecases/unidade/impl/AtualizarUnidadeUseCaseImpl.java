package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.exceptions.UnidadeNotFoundException;
import com.seplag.processoseletivo.application.usecases.unidade.AtualizarUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.infra.persistence.entity.UnidadeEntity;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUnidadeUseCaseImpl implements AtualizarUnidadeUseCase {

    private final UnidadeRepository unidadeRepository;

    public AtualizarUnidadeUseCaseImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }


    @Override
    public UnidadeResponseDto execute(Long unid_id, UnidadeRequestDto unidadeRequestDto) {
        Unidade unidade = buscaUnidade(unid_id);
        unidade.setUni_sigla(unidadeRequestDto.unid_sigla());
        unidade.setUnid_nome(unidadeRequestDto.unid_nome());

        Unidade unidadeAtualizada = unidadeRepository.atualizar(unidade);

        return UnidadeResponseDto.of(unidadeAtualizada);

    }


    private Unidade buscaUnidade(Long id) {
        return unidadeRepository.buscarPorId(id)
                .orElseThrow(() -> new UnidadeNotFoundException("Unidade não encontrada"));
    }



}
