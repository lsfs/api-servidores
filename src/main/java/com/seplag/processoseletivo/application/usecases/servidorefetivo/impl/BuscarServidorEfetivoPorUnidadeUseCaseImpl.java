package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.BuscarServidorEfetivoPorUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class BuscarServidorEfetivoPorUnidadeUseCaseImpl implements BuscarServidorEfetivoPorUnidadeUseCase {

    private final LotacaoRepository lotacaoRepository;

    public BuscarServidorEfetivoPorUnidadeUseCaseImpl(LotacaoRepository lotacaoRepository) {
        this.lotacaoRepository = lotacaoRepository;
    }


    @Override
    public RespostaPaginada<ServidorEfetivoDetailsResponseDto> execute(Long unidadeId, int pagina, int tamanho) {

        RespostaPaginada<Lotacao> lotacoes = lotacaoRepository.buscaLotacoesComServidoresEfetivosPorUnidade(unidadeId, pagina, tamanho);

        List<ServidorEfetivoDetailsResponseDto> servidoresEfetivos = lotacoes.getContent()
                .stream()
                .filter(lotacao -> lotacao.getPessoa() != null)
                .map(lotacao -> {
                    int idade = lotacao.getPessoa().getIdade();

                    return new ServidorEfetivoDetailsResponseDto(
                            lotacao.getPessoa().getPes_nome(),
                            idade,
                            UnidadeResponseDto.simpleDetailsOf(lotacao.getUnidade())
                    );

                }).toList();

        return new RespostaPaginada<>(servidoresEfetivos, lotacoes.getPageNumber(), lotacoes.getTotalPages(), lotacoes.getTotalElements());

    }
}
