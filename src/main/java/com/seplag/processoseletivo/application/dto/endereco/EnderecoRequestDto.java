package com.seplag.processoseletivo.application.dto.endereco;

import com.seplag.processoseletivo.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para requisição de endereço")
public record EnderecoRequestDto(
        @Schema(description = "Tipo de logradouro", example = "Rua", required = true)
        String end_tipo_logradouro,

        @Schema(description = "Logradouro", example = "Avenida Paulista", required = true)
        String end_logradouro,

        @Schema(description = "Número", example = "123", required = true)
        Long end_numero,

        @Schema(description = "Bairro", example = "Centro", required = true)
        String end_bairro,

        @Schema(description = "ID da cidade", example = "1", required = true)
        Long cidade_id
) {
    public EnderecoRequestDto {
        if (end_tipo_logradouro == null || end_tipo_logradouro.isBlank()) {
            throw new IllegalArgumentException("Tipo de logradouro não pode ser nulo ou vazio");
        }
        if (end_logradouro == null || end_logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro não pode ser nulo ou vazio");
        }
        if (end_numero <= 0) {
            throw new IllegalArgumentException("Número deve ser maior que zero");
        }
        if (end_bairro == null || end_bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro não pode ser nulo ou vazio");
        }
        if (cidade_id == null) {
            throw new IllegalArgumentException("ID da cidade não pode ser nulo");
        }
    }

    public Endereco fromDto(EnderecoRequestDto enderecoRequestDto) {
        Endereco endereco = new Endereco();
        endereco.setEnd_tipo_logradouro(enderecoRequestDto.end_tipo_logradouro());
        endereco.setEnd_logradouro(enderecoRequestDto.end_logradouro());
        endereco.setEnd_numero(enderecoRequestDto.end_numero());
        endereco.setEnd_bairro(enderecoRequestDto.end_bairro());
        return endereco;
    }

}
