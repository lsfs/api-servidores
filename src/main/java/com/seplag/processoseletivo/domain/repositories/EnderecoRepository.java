package com.seplag.processoseletivo.domain.repositories;

import com.seplag.processoseletivo.domain.model.Endereco;

public interface EnderecoRepository {

    Endereco criar(Endereco endereco);
    Endereco atualizar(Endereco endereco);

}
