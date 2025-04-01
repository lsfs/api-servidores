package com.seplag.processoseletivo.application.usecases.lotacao.impl;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.AtualizarLotacaoUseCase;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.repositories.PessoaRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarLotacaoUseCaseImpl implements AtualizarLotacaoUseCase {

    private final LotacaoRepository lotacaoRepository;
    private final PessoaRepository pessoaRepository;
    private final UnidadeRepository unidadeRepository;

    public AtualizarLotacaoUseCaseImpl(LotacaoRepository lotacaoRepository, PessoaRepository pessoaRepository, UnidadeRepository unidadeRepository) {
        this.lotacaoRepository = lotacaoRepository;
        this.pessoaRepository = pessoaRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public LotacaoResponseDto execute(Long id, LotacaoRequestDto lotacaoRequestDto) {

        Lotacao lotacao = buscaLotacao(id);
        lotacao.setLot_data_lotacao(lotacaoRequestDto.lot_data_lotacao());
        lotacao.setLot_data_remocao(lotacaoRequestDto.lot_data_remocao());
        lotacao.setLot_portaria(lotacaoRequestDto.lot_portaria());

        Pessoa pessoa = buscaPessoa(lotacaoRequestDto.pes_id());
        lotacao.setPessoa(pessoa);

        Unidade unidade = buscaUnidade(lotacaoRequestDto.uni_id());
        lotacao.setUnidade(unidade);

        Lotacao lotacaoAtualizada = lotacaoRepository.atualizar(lotacao);
        return LotacaoResponseDto.of(lotacaoAtualizada);

    }


    private Lotacao buscaLotacao(Long id) {
        return lotacaoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Lotação não encontrada"));
    }

    private Pessoa buscaPessoa(Long id) {
        return pessoaRepository.buscarPorId(id);
    }

    private Unidade buscaUnidade(Long id) {
        return unidadeRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

    }
}
