package com.seplag.processoseletivo.infra.mapper;

import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.infra.persistence.entity.EnderecoEntity;

public class EnderecoMapper {

    public static EnderecoEntity toEntity(Endereco endereco) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setEnd_id(endereco.getEnd_id());
        entity.setEnd_logradouro(endereco.getEnd_logradouro());
        entity.setEnd_numero(endereco.getEnd_numero());
        entity.setEnd_tipo_logradouro(endereco.getEnd_tipo_logradouro());
        entity.setEnd_bairro(endereco.getEnd_bairro());
        if (endereco.getCidade() != null) {
            entity.setCidade(CidadeMapper.toEntity(endereco.getCidade()));
        }
        return entity;
    }

    public static Endereco toModel(EnderecoEntity enderecoEntity) {
        Endereco endereco = new Endereco();
        endereco.setEnd_id(enderecoEntity.getEnd_id());
        endereco.setEnd_logradouro(enderecoEntity.getEnd_logradouro());
        endereco.setEnd_numero(enderecoEntity.getEnd_numero());
        endereco.setEnd_tipo_logradouro(enderecoEntity.getEnd_tipo_logradouro());
        endereco.setEnd_bairro(enderecoEntity.getEnd_bairro());
        endereco.setCidade(CidadeMapper.toModel(enderecoEntity.getCidade()));
        return endereco;
    }
}
