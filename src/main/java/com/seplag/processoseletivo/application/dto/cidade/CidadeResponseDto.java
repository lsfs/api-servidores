package com.seplag.processoseletivo.application.dto.cidade;

import com.seplag.processoseletivo.domain.model.Cidade;

public record CidadeResponseDto(
        Long cid_id,
        String cid_nome,
        String cid_uf
) {

    public static CidadeResponseDto of(final Cidade cidade) {
        return new CidadeResponseDto(
                cidade.getCid_id(),
                cidade.getCid_nome(),
                cidade.getCid_uf()
        );
    }

}
