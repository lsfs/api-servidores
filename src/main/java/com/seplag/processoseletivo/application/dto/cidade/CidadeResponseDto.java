package com.seplag.processoseletivo.application.dto.cidade;

import com.seplag.processoseletivo.domain.model.Cidade;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta de cidade")
public record CidadeResponseDto(
        @Schema(description = "ID da cidade", example = "1")
        Long cid_id,

        @Schema(description = "Nome da cidade", example = "Cuiabá")
        String cid_nome,

        @Schema(description = "UF da cidade", example = "MT")
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