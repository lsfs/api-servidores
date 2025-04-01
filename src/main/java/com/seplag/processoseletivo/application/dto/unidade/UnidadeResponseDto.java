package com.seplag.processoseletivo.application.dto.unidade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;

import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UnidadeResponseDto (
        Long unid_id,
        String unid_nome,
        String unid_sigla,
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
