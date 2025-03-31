package com.seplag.processoseletivo.infra.mapper;

import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorTemporarioEntity;

public class ServidorTemporarioMapper {

    public static ServidorTemporario toModel(ServidorTemporarioEntity entity) {
        if (entity == null) {
            return null;
        }

        ServidorTemporario servidorTemporario = new ServidorTemporario();
        servidorTemporario.setPessoa(PessoaMapper.toModel(entity.getPessoa()));
        servidorTemporario.setSt_data_admissao(entity.getSt_data_admissao());
        servidorTemporario.setSt_data_demissao(entity.getSt_data_demissao());

        return servidorTemporario;
    }

}
