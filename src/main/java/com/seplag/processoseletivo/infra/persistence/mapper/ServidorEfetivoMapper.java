package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorEfetivoEntity;

public class ServidorEfetivoMapper {

    public static ServidorEfetivo toModel(ServidorEfetivoEntity entity) {
        if (entity == null) {
            return null;
        }

        ServidorEfetivo servidorEfetivo = new ServidorEfetivo();
        servidorEfetivo.setPessoa(PessoaMapper.toModel(entity.getPessoa()));
        servidorEfetivo.setSe_matricula(entity.getSe_matricula());

        return servidorEfetivo;
    }

}
