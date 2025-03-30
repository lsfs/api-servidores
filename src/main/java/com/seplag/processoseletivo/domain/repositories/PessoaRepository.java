package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Pessoa;

public interface PessoaRepository {
    Pessoa criar(Pessoa pessoa);
    Pessoa buscarPorId(Long id);
    Pessoa atualizar(Pessoa pessoa);
}

