package com.seplag.processoseletivo.application.usecases.lotacao.impl;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.CriaLotacaoUseCase;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.repositories.PessoaRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CriaLotacaoUseCaseimpl implements CriaLotacaoUseCase {

    private final LotacaoRepository lotacaoRepository;
    private final PessoaRepository pessoaRepository;
    private final UnidadeRepository unidadeRepository;

    public CriaLotacaoUseCaseimpl(LotacaoRepository lotacaoRepository, PessoaRepository pessoaRepository, UnidadeRepository unidadeRepository) {
        this.lotacaoRepository = lotacaoRepository;
        this.pessoaRepository = pessoaRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public LotacaoResponseDto execute(LotacaoRequestDto request) {

        Lotacao lotacao = new Lotacao();
        lotacao.setLot_data_lotacao(request.lot_data_lotacao());
        lotacao.setLot_data_remocao(request.lot_data_remocao());
        lotacao.setLot_portaria(request.lot_portaria());

        lotacao.setPessoa(buscarPessoa(request.pes_id()));
        lotacao.setUnidade(buscarUnidade(request.uni_id()));

        Lotacao lotacaoCriada = lotacaoRepository.criar(lotacao);
        return LotacaoResponseDto.of(lotacaoCriada);

    }

    private Unidade buscarUnidade(Long id) {
        return unidadeRepository.buscarPorId(id)
                .orElseThrow(()-> new EntityNotFoundException("Unidade não encontrada com o id: " + id));
    }


    private Pessoa buscarPessoa(Long id) {
        return pessoaRepository.buscarPorId(id);
    }
}
