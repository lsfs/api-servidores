package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.repositories.LotacaoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.persistence.entity.LotacaoEntity;
import com.seplag.processoseletivo.infra.persistence.mapper.LotacaoMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.UnidadeMapper;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.LotacaoJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LotacaoRepositoryImpl implements LotacaoRepository {

    private final LotacaoJpaRepository lotacaoJpaRepository;

    public LotacaoRepositoryImpl(LotacaoJpaRepository lotacaoJpaRepository) {
        this.lotacaoJpaRepository = lotacaoJpaRepository;
    }

    @Override
    public Lotacao criar(Lotacao lotacao) {

        LotacaoEntity lotacaoEntity = new LotacaoEntity();
        lotacaoEntity.setLot_data_lotacao(lotacao.getLot_data_lotacao());
        lotacaoEntity.setLot_data_remocao(lotacao.getLot_data_remocao());
        lotacaoEntity.setLot_portaria(lotacao.getLot_portaria());
        lotacaoEntity.setPessoa(PessoaMapper.toEntity(lotacao.getPessoa()));
        lotacaoEntity.setUnidade(UnidadeMapper.toEntity(lotacao.getUnidade()));

        var unidadeSalva = this.lotacaoJpaRepository.save(lotacaoEntity);
        return LotacaoMapper.toModel(unidadeSalva);
    }

    @Override
    public Optional<Lotacao> buscarPorId(Long id) {
        Optional<LotacaoEntity> lotacaoEntity = lotacaoJpaRepository.findById(id);
        return lotacaoEntity.map(LotacaoMapper::toModel);
    }

    @Override
    public RespostaPaginada<Lotacao> buscaLotacoes(int pagina, int tamanho) {

        var respostaPaginada = lotacaoJpaRepository.findAll(PageRequest.of(pagina, tamanho));
        List<Lotacao> lotacoes = respostaPaginada.getContent().stream()
                .map(LotacaoMapper::toModel)
                .toList();

        return new RespostaPaginada<>(lotacoes, respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }

    @Override
    public RespostaPaginada<Lotacao> buscaLotacoesComServidoresEfetivosPorUnidade(Long unidadeId, int pagina, int tamanho) {

        var pageable = PageRequest.of(pagina, tamanho);
        var result = lotacaoJpaRepository.buscaLotacaoesComServidoresEfetivosPorUnidadeId(unidadeId, pageable);

        List<Lotacao> content = result.getContent().stream()
                .map(LotacaoMapper::toModel)
                .collect(Collectors.toList());

        return new RespostaPaginada<>(
                content,
                result.getNumber(),
                result.getTotalPages(),
                result.getTotalElements()
        );
    }

    @Override
    public Lotacao atualizar(Lotacao lotacao) {
        LotacaoEntity lotacaoEntity = new LotacaoEntity();
        lotacaoEntity.setLot_id(lotacao.getLot_id());
        lotacaoEntity.setLot_data_lotacao(lotacao.getLot_data_lotacao());
        lotacaoEntity.setLot_data_remocao(lotacao.getLot_data_remocao());
        lotacaoEntity.setLot_portaria(lotacao.getLot_portaria());
        lotacaoEntity.setPessoa(PessoaMapper.toEntity(lotacao.getPessoa()));
        lotacaoEntity.setUnidade(UnidadeMapper.toEntity(lotacao.getUnidade()));

        var unidadeSalva = this.lotacaoJpaRepository.save(lotacaoEntity);
        return LotacaoMapper.toModel(unidadeSalva);
    }
}
