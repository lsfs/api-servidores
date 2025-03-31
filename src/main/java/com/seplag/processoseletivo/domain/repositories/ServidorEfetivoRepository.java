package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface ServidorEfetivoRepository {

    ServidorEfetivo criar(ServidorEfetivo servidor);
    ServidorEfetivo buscarPorId(Long id);
    ServidorEfetivo atualizar(Long id, ServidorEfetivo servidor);
    RespostaPaginada<ServidorEfetivo> buscaServidores(int pagina, int tamanho);
    void deletar(Long id);
    RespostaPaginada<ServidorEfetivo> listarPorUnidade(Long unid_id, int pagina, int tamanho);


}
