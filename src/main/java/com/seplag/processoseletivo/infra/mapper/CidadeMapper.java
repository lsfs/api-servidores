package com.seplag.processoseletivo.infra.mapper;

import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.infra.persistence.entity.CidadeEntity;

public class CidadeMapper {

    public static Cidade toModel(CidadeEntity entity) {
        Cidade cidade = new Cidade();
        cidade.setCid_id(entity.getCid_id());
        cidade.setCid_nome(entity.getCid_nome());
        cidade.setCid_uf(entity.getCid_uf());
        return cidade;
    }

    public static CidadeEntity toEntity(Cidade cidade) {
        CidadeEntity entity = new CidadeEntity();
        entity.setCid_id(cidade.getCid_id());
        entity.setCid_nome(cidade.getCid_nome());
        entity.setCid_uf(cidade.getCid_uf());
        return entity;
    }

}
