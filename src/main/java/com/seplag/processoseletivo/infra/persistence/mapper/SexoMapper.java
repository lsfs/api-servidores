package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.enums.Sexo;

public class SexoMapper {

    public static Sexo toSexo(String sexo) {
        try {
            return Sexo.valueOf(sexo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para sexo: " + sexo, e);
        }
    }
}
