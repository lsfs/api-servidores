package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

public interface EnderecoRepository {

    Endereco criar(Endereco endereco);
    Endereco atualizar(Endereco endereco);
    RespostaPaginada<Endereco> buscar(int pagina, int tamanho);
    Endereco buscarPorId(Long id);

}
