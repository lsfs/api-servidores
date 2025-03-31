package com.seplag.processoseletivo.application.dto.endereco;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.domain.model.Endereco;

public record EnderecoResponseDto (
        Long end_id,
        String end_logradouro,
        String end_tipo_logradouro,
        Long end_numero,
        String end_bairro,
        CidadeResponseDto cidade
) {

    public static EnderecoResponseDto of(Endereco endereco) {
        return new EnderecoResponseDto(
                endereco.getEnd_id(),
                endereco.getEnd_logradouro(),
                endereco.getEnd_tipo_logradouro(),
                endereco.getEnd_numero(),
                endereco.getEnd_bairro(),
                CidadeResponseDto.of(endereco.getCidade())
        );
    }
}
