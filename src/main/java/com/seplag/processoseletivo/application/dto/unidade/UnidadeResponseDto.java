package com.seplag.processoseletivo.application.dto.unidade;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Unidade;

import java.util.Set;
import java.util.stream.Collectors;

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
}
