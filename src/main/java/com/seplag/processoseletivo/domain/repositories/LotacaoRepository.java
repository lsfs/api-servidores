package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

import java.util.Optional;

public interface LotacaoRepository {

    Lotacao criar(Lotacao lotacao);
    Optional<Lotacao> buscarPorId(Long id);
    RespostaPaginada<Lotacao> buscaLotacoes(int pagina, int tamanho);
    Lotacao atualizar(Lotacao lotacao);

}
