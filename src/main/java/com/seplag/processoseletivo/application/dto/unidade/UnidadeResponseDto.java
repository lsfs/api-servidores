package com.seplag.processoseletivo.application.dto.unidade;

import com.seplag.processoseletivo.domain.model.Unidade;

public record UnidadeResponseDto (
        Long unid_id,
        String unid_nome,
        String unid_sigla
) {
    public static UnidadeResponseDto of(Unidade unidadeCriada) {
        return new UnidadeResponseDto(
                unidadeCriada.getUnid_id(),
                unidadeCriada.getUnid_nome(),
                unidadeCriada.getUni_sigla()
        );
    }
}
