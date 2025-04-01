package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.model.FotoPessoa;
import com.seplag.processoseletivo.infra.persistence.entity.FotoPessoaEntity;

public class FotoPessoaMapper {

    public static FotoPessoa toDomain(FotoPessoaEntity fotoPessoaEntity) {
        if (fotoPessoaEntity == null) {
            return null;
        }
        return new FotoPessoa(
                fotoPessoaEntity.getFp_id(),
                PessoaMapper.toModel(fotoPessoaEntity.getPessoa()),
                fotoPessoaEntity.getFp_data(),
                fotoPessoaEntity.getFp_bucket(),
                fotoPessoaEntity.getFp_hash()
        );
    }

    public static FotoPessoaEntity toEntity(FotoPessoa fotoPessoa) {
        if (fotoPessoa == null) {
            return null;
        }
        FotoPessoaEntity fotoPessoaEntity = new FotoPessoaEntity();
        fotoPessoaEntity.setFp_data(fotoPessoa.getFp_data());
        fotoPessoaEntity.setFp_bucket(fotoPessoa.getFp_bucket());
        fotoPessoaEntity.setFp_hash(fotoPessoa.getFp_hash());
        return fotoPessoaEntity;

    }
}
