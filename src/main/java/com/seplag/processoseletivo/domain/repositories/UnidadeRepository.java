package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UnidadeRepository {

    Unidade criar(Unidade unidade);
    Optional<Unidade> buscarPorId(Long id);
    RespostaPaginada<Unidade> buscaUnidades(int pagina, int tamanho);
    Unidade atualizar(Unidade unidade);
    void deletar(Long idUnidade);
}
