package com.seplag.processoseletivo.application.usecases.endereco.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.endereco.BuscarEnderecoPorIdUseCase;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.infra.persistence.mapper.EnderecoMapper;
import org.springframework.stereotype.Service;

@Service
public class BuscarEnderecoPorIdUseCaseImpl implements BuscarEnderecoPorIdUseCase {

    private final EnderecoRepository enderecoRepository;

    public BuscarEnderecoPorIdUseCaseImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public EnderecoResponseDto execute(Long id) {

        return EnderecoResponseDto.of(enderecoRepository.buscarPorId(id));

    }
}
