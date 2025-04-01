package com.seplag.processoseletivo.application.usecases.lotacao.impl;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoUseCase;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarLotacaoUseCaseImpl implements BuscarLotacaoUseCase {

    private final LotacaoRepository lotacaoRepository;

    public BuscarLotacaoUseCaseImpl(LotacaoRepository lotacaoRepository) {
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public RespostaPaginada<LotacaoResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<Lotacao> respostaPaginada = lotacaoRepository.buscaLotacoes(pagina, tamanho);
        var lotacoes = respostaPaginada.getContent()
                .stream()
                .map(LotacaoResponseDto::of)
                .toList();

        return new RespostaPaginada<>(lotacoes, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }

}
