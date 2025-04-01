package com.seplag.processoseletivo.application.usecases.lotacao.impl;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoPorIdUseCase;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarLotacaoPorIdUseCaseImpl implements BuscarLotacaoPorIdUseCase {

    private final LotacaoRepository lotacaoRepository;

    public BuscarLotacaoPorIdUseCaseImpl(LotacaoRepository lotacaoRepository) {
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public LotacaoResponseDto execute(Long id) {

        Lotacao lotacao = lotacaoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Lotação não encontrada"));

        return LotacaoResponseDto.of(lotacao);
    }
}
