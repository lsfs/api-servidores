package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface ServidorEfetivoRepository {

    ServidorEfetivo criar(ServidorEfetivo servidor);
    ServidorEfetivo buscarPorId(Long id);
    ServidorEfetivo atualizar(ServidorEfetivo servidor);
    RespostaPaginada<ServidorEfetivo> buscaServidores(int pagina, int tamanho);
    RespostaPaginada<ServidorEfetivo> listarPorUnidade(Long unid_id, int pagina, int tamanho);


}
