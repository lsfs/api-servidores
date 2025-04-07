package com.seplag.processoseletivo.application.dto.unidade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.model.Unidade;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para resposta de unidade")
public record UnidadeResponseDto (
        @Schema(description = "ID da unidade", example = "1")
        Long unid_id,

        @Schema(description = "Nome da unidade", example = "Departamento Financeiro")
        String unid_nome,

        @Schema(description = "Sigla da unidade", example = "FIN")
        String unid_sigla,

        @Schema(description = "Endereços da unidade")
        Set<EnderecoResponseDto> enderecos
) {
    public static UnidadeResponseDto of(Unidade unidadeCriada) {
        return new UnidadeResponseDto(
                unidadeCriada.getUnid_id(),
                unidadeCriada.getUnid_nome(),
                unidadeCriada.getUni_sigla(),
                unidadeCriada.getEnderecos().stream()
                        .map(EnderecoResponseDto::of)
                        .collect(Collectors.toSet())
        );
    }

    public static UnidadeResponseDto simpleDetailsOf(Unidade unidade) {
        return new UnidadeResponseDto(
                unidade.getUnid_id(),
                unidade.getUnid_nome(),
                unidade.getUni_sigla(),
                null
        );
    }

    public static UnidadeResponseDto enderecoDetails(Unidade unidade) {
        return new UnidadeResponseDto(
                null,
                unidade.getUnid_nome(),
                null,
                unidade.getEnderecos().stream()
                        .map(EnderecoResponseDto::of)
                        .collect(Collectors.toSet())
        );
    }

}
