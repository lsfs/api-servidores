package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

import java.util.Optional;

public interface CidadeRepository {
    Cidade criar(Cidade cidade);
    Optional<Cidade> buscarPorId(Long id);
    RespostaPaginada<Cidade> buscarCidades(int pagina, int tamanho);
    Cidade atualizar(Long id, Cidade cidade);

}
