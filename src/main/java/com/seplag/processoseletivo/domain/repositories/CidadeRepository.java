package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Cidade;

public interface CidadeRepository {
    Cidade criar(Cidade cidade);
    Cidade buscarPorId(Long id);

}
