package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.FotoPessoa;

import java.util.Optional;

public interface FotoPessoaRepository {

    FotoPessoa salvar(FotoPessoa fotoPessoa);
    Optional<FotoPessoa> buscarPorPessoa(Long id);
    Optional<FotoPessoa> buscarPorId(Long id);

}
