package com.seplag.processoseletivo.application.dto.endereco;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta de endereço")
public record EnderecoResponseDto (
        @Schema(description = "ID do endereço", example = "1")
        Long end_id,

        @Schema(description = "Logradouro", example = "13 de Junho")
        String end_logradouro,

        @Schema(description = "Tipo de logradouro", example = "Rua")
        String end_tipo_logradouro,

        @Schema(description = "Número", example = "123")
        Long end_numero,

        @Schema(description = "Bairro", example = "Centro")
        String end_bairro,

        @Schema(description = "Cidade")
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
