package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.fotopessoa.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.fotopessoa.BuscaFotoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.BuscarServidorEfetivoPorUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.FotoPessoa;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.repositories.FotoPessoaRepository;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class BuscarServidorEfetivoPorUnidadeUseCaseImpl implements BuscarServidorEfetivoPorUnidadeUseCase {

    private final LotacaoRepository lotacaoRepository;
    private final FotoPessoaRepository fotoPessoaRepository;
    private final BuscaFotoPorIdUseCase buscaFotoPorIdUseCase;

    public BuscarServidorEfetivoPorUnidadeUseCaseImpl(LotacaoRepository lotacaoRepository, FotoPessoaRepository fotoPessoaRepository, BuscaFotoPorIdUseCase buscaFotoPorIdUseCase) {
        this.lotacaoRepository = lotacaoRepository;
        this.fotoPessoaRepository = fotoPessoaRepository;
        this.buscaFotoPorIdUseCase = buscaFotoPorIdUseCase;
    }


    @Override
    public RespostaPaginada<ServidorEfetivoDetailsResponseDto> execute(Long unidadeId, int pagina, int tamanho) {

        RespostaPaginada<Lotacao> lotacoes = lotacaoRepository.buscaLotacoesComServidoresEfetivosPorUnidade(unidadeId, pagina, tamanho);


        List<ServidorEfetivoDetailsResponseDto> servidoresEfetivos = lotacoes.getContent()
                .stream()
                .filter(lotacao -> lotacao.getPessoa() != null)
                .map(lotacao -> {
                    Pessoa pessoa = lotacao.getPessoa();
                    int idade = pessoa.getIdade();
                    List<FotoPessoa> fotoPessoasList = fotoPessoaRepository.buscarPorPessoa(pessoa.getPes_id());
                    List<FotoPessoaLinkResponse> listaLinks = List.of();

                    if (!fotoPessoasList.isEmpty()) {
                        listaLinks = fotoPessoasList.stream().map(
                                fotoPessoa -> {
                                    String fotoUrl = null;
                                    if (fotoPessoa != null) {
                                        fotoUrl = buscaFotoPorIdUseCase.execute(fotoPessoa.getFp_id());
                                    }
                                    return new FotoPessoaLinkResponse(fotoUrl);
                                }
                        ).toList();
                    }

                    return new ServidorEfetivoDetailsResponseDto(
                            lotacao.getPessoa().getPes_nome(),
                            idade,
                            UnidadeResponseDto.simpleDetailsOf(lotacao.getUnidade()),
                            listaLinks
                    );

                }).toList();

        return new RespostaPaginada<>(servidoresEfetivos, lotacoes.getPageNumber(), lotacoes.getTotalPages(), lotacoes.getTotalElements());

    }
}
