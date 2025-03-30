package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.exceptions.UnidadeNotFoundException;
import com.seplag.processoseletivo.application.usecases.unidade.BuscarUnidadePorIdUseCase;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarUnidadePorIdUseCaseImpl implements BuscarUnidadePorIdUseCase {

    private final UnidadeRepository repository;

    public BuscarUnidadePorIdUseCaseImpl(UnidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public UnidadeResponseDto execute(Long id) {

        Unidade unidade = repository.buscarPorId(id)
                .orElseThrow(() -> new UnidadeNotFoundException("Unidade não encontrada"));

        return UnidadeResponseDto.of(unidade);
    }

}
