package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.FotoPessoa;

import java.util.List;
import java.util.Optional;

public interface FotoPessoaRepository {

    FotoPessoa salvar(FotoPessoa fotoPessoa);
    List<FotoPessoa> buscarPorPessoa(Long id);
    Optional<FotoPessoa> buscarPorId(Long id);

}
