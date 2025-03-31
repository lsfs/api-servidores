package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.infra.persistence.entity.UnidadeEntity;

import java.util.stream.Collectors;

public class UnidadeMapper {


    public static Unidade toModel(UnidadeEntity entity) {

        Unidade unidade = new Unidade();
        unidade.setUnid_id(entity.getUnid_id());
        unidade.setUnid_nome(entity.getUnid_nome());
        unidade.setUni_sigla(entity.getUnid_sigla());
        unidade.setEnderecos(
                entity.getEnderecos()
                        .stream()
                        .map(EnderecoMapper::toModel)
                        .collect(Collectors.toSet())
        );

        return unidade;

    }

    public static UnidadeEntity toEntity(Unidade unidade) {

        UnidadeEntity unidadeEntity = new UnidadeEntity();
        unidadeEntity.setUnid_id(unidade.getUnid_id());
        unidadeEntity.setUnid_nome(unidade.getUnid_nome());
        unidadeEntity.setUnid_sigla(unidade.getUni_sigla());

        return unidadeEntity;

    }


}
