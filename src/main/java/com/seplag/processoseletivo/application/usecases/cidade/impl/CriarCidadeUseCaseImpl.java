package com.seplag.processoseletivo.application.usecases.cidade.impl;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.CriarCidadeUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarCidadeUseCaseImpl implements CriarCidadeUseCase {

    private final CidadeRepository cidadeRepository;

    public CriarCidadeUseCaseImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public CidadeResponseDto execute(CidadeRequestDto cidadeRequestDto) {

        Cidade cidade = new Cidade();
        cidade.setCid_nome(cidadeRequestDto.cid_nome());
        cidade.setCid_uf(cidadeRequestDto.cid_uf());
        return CidadeResponseDto.of(cidadeRepository.criar(cidade));

    }
}
