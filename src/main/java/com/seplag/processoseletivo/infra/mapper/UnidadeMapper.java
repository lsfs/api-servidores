package com.seplag.processoseletivo.infra.mapper;

import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.infra.persistence.entity.UnidadeEntity;

public class UnidadeMapper {


    public static Unidade toModel(UnidadeEntity entity) {

        Unidade unidade = new Unidade();
        unidade.setUnid_id(entity.getUnid_id());
        unidade.setUnid_nome(entity.getUnid_nome());
        unidade.setUni_sigla(entity.getUnid_sigla());

        return unidade;

    }


}
