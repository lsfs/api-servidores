package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.FotoPessoa;

public interface FotoPessoaRepository {

    FotoPessoa criar(FotoPessoa fotoPessoa);
    FotoPessoa buscarPorId(Long id);

}
