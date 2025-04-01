package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.persistence.mapper.EnderecoMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.UnidadeMapper;
import com.seplag.processoseletivo.infra.persistence.entity.UnidadeEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.UnidadeJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnidadeRepositoryImpl implements UnidadeRepository {

    UnidadeJpaRepository unidadeRepository;

    public UnidadeRepositoryImpl(UnidadeJpaRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Unidade criar(Unidade unidade) {

        UnidadeEntity unidadeEntity = new UnidadeEntity();
        unidadeEntity.setUnid_nome(unidade.getUnid_nome());
        unidadeEntity.setUnid_sigla(unidade.getUni_sigla());
        unidadeEntity.setEnderecos(unidade.getEnderecos()
                .stream().map(EnderecoMapper::toEntity)
                .collect(Collectors.toSet()));

        UnidadeEntity unidadeEntitySalva = unidadeRepository.save(unidadeEntity);
        return UnidadeMapper.toModel(unidadeEntitySalva);

    }

    @Override
    public Optional<Unidade> buscarPorId(Long id) {

        Optional<UnidadeEntity> unidadeEntity = unidadeRepository.findById(id);
        return unidadeEntity.map(UnidadeMapper::toModel);

    }

    @Override
    public RespostaPaginada<Unidade> buscaUnidades(int pagina, int tamanho) {

        var respostaPaginada = unidadeRepository.findAll(PageRequest.of(pagina, tamanho));
        List<Unidade> unidades = respostaPaginada.getContent().stream()
                .map(UnidadeMapper::toModel)
                .toList();

        return new RespostaPaginada<>(unidades, respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }

    @Override
    public Unidade atualizar(Unidade unidade) {

        UnidadeEntity unidadeEntity = UnidadeMapper.toEntity(unidade);
        unidadeEntity.setEnderecos(unidade.getEnderecos()
                .stream().map(EnderecoMapper::toEntity)
                .collect(Collectors.toSet()));

        UnidadeEntity unidadeEntitySalva = unidadeRepository.save(unidadeEntity);
        return UnidadeMapper.toModel(unidadeEntitySalva);

    }

    @Override
    public void deletar(Long idUnidade) {

        var unidadeEntity = unidadeRepository.findById(idUnidade)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));

        unidadeRepository.delete(unidadeEntity);
    }

    @Override
    public List<Unidade> buscaPorPessoaId(Long pesId) {
        return unidadeRepository.buscaPorPessoaId(pesId)
                .stream()
                .map(UnidadeMapper::toModel)
                .collect(Collectors.toList());
    }
}
