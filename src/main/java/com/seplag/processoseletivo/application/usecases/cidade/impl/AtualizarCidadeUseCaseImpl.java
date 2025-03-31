package com.seplag.processoseletivo.application.usecases.cidade.impl;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.AtualizarCidadeUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarCidadeUseCaseImpl implements AtualizarCidadeUseCase {

    private final CidadeRepository cidadeRepository;

    public AtualizarCidadeUseCaseImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public CidadeResponseDto execute(Long id, CidadeRequestDto cidadeRequestDto) {

        Cidade cidade = buscaCidade(id);
        cidade.setCid_nome(cidadeRequestDto.cid_nome());
        cidade.setCid_uf(cidadeRequestDto.cid_uf());

        Cidade cidadeAtualizada = cidadeRepository.atualizar(id, cidade);

        return CidadeResponseDto.of(cidadeAtualizada);

    }


    private Cidade buscaCidade(Long id) {
        return cidadeRepository.buscarPorId(id);
    }
}
