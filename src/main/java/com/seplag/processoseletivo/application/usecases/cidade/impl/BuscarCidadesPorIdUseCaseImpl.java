package com.seplag.processoseletivo.application.usecases.cidade.impl;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesPorIdUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarCidadesPorIdUseCaseImpl implements BuscarCidadesPorIdUseCase {

    private final CidadeRepository cidadeRepository;

    public BuscarCidadesPorIdUseCaseImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public CidadeResponseDto execute(Long id) {

        Cidade cidade = cidadeRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));

        return CidadeResponseDto.of(cidade);

    }
}
