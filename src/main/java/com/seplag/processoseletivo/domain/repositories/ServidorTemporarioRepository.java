package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface ServidorTemporarioRepository {

    ServidorTemporario criar(ServidorTemporario servidorTemporario);
    ServidorTemporario buscarPorId(Long id);
    RespostaPaginada<ServidorTemporario> buscaServidoresTemporarios(int pagina, int tamanho);
    ServidorTemporario atualizar(Long id, ServidorTemporario servidorTemporario);
    void deletar(Long id);

}
