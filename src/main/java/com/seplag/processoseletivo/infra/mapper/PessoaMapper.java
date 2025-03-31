package com.seplag.processoseletivo.infra.mapper;

import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;

import java.util.stream.Collectors;

public class PessoaMapper {

    public static Pessoa toModel(PessoaEntity pessoaEntity) {
        if (pessoaEntity == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_id(pessoaEntity.getPes_id());
        pessoa.setPes_nome(pessoaEntity.getPes_nome());
        pessoa.setPes_data_nascimento(pessoaEntity.getPes_data_nascimento());
        pessoa.setPes_sexo(pessoaEntity.getPes_sexo());
        pessoa.setPes_mae(pessoaEntity.getPes_mae());
        pessoa.setPes_pai(pessoaEntity.getPes_pai());
        pessoa.setEnderecos(
               pessoaEntity.getPes_enderecos().stream().map(EnderecoMapper::toModel).collect(Collectors.toSet())
        );

        return pessoa;
    }

    public static PessoaEntity toEntity(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setPes_id(pessoa.getPes_id());
        pessoaEntity.setPes_nome(pessoa.getPes_nome());
        pessoaEntity.setPes_data_nascimento(pessoa.getPes_data_nascimento());
        pessoaEntity.setPes_sexo(pessoa.getPes_sexo());
        pessoaEntity.setPes_mae(pessoa.getPes_mae());
        pessoaEntity.setPes_pai(pessoa.getPes_pai());

        pessoaEntity.setPes_enderecos(
                pessoa.getEnderecos().stream().map(EnderecoMapper::toEntity).collect(Collectors.toSet())
        );


        return pessoaEntity;
    }
}
