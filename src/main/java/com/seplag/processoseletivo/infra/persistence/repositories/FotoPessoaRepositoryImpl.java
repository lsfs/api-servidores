package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.domain.model.FotoPessoa;
import com.seplag.processoseletivo.domain.repositories.FotoPessoaRepository;
import com.seplag.processoseletivo.infra.persistence.entity.FotoPessoaEntity;
import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;
import com.seplag.processoseletivo.infra.persistence.mapper.FotoPessoaMapper;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.FotoPessoaJpaRepository;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.PessoaJpaRepository;

import java.util.Optional;

public class FotoPessoaRepositoryImpl implements FotoPessoaRepository {

    private final FotoPessoaJpaRepository fotoPessoaJpaRepository;
    private final PessoaJpaRepository pessoaJpaRepository;

    public FotoPessoaRepositoryImpl(FotoPessoaJpaRepository fotoPessoaJpaRepository, PessoaJpaRepository pessoaJpaRepository) {
        this.fotoPessoaJpaRepository = fotoPessoaJpaRepository;
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public FotoPessoa salvar(FotoPessoa fotoPessoa) {

        var pessoa = pessoaJpaRepository.findById(fotoPessoa.getPessoa().getPes_id())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        var fotoPessoaEntity = FotoPessoaMapper.toEntity(fotoPessoa);
        fotoPessoaEntity.setPessoa(pessoa);

        return FotoPessoaMapper.toDomain(fotoPessoaJpaRepository.save(fotoPessoaEntity));

    }

    @Override
    public Optional<FotoPessoa> buscarPorPessoa(Long idPessoa) {

        return fotoPessoaJpaRepository.findByPessoaId(idPessoa)
                .map(FotoPessoaMapper::toDomain);


    }

    @Override
    public Optional<FotoPessoa> buscarPorId(Long id) {

        var fotoPessoaEntity = fotoPessoaJpaRepository.findById(id);
        return fotoPessoaEntity.map(FotoPessoaMapper::toDomain);

    }
}
