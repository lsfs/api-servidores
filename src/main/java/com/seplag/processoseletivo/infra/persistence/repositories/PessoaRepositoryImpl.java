package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.repositories.PessoaRepository;
import com.seplag.processoseletivo.infra.persistence.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.PessoaJpaRepository;

public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepository pessoaJpaRepository;

    public PessoaRepositoryImpl(PessoaJpaRepository pessoaJpaRepository) {
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public Pessoa criar(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa buscarPorId(Long id) {

        PessoaEntity pessoaEntity = pessoaJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        return PessoaMapper.toModel(pessoaEntity);

    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        return null;
    }
}
