package com.seplag.processoseletivo.application.dto.endereco;

import com.seplag.processoseletivo.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para requisição de endereço")
public record EnderecoRequestUpdateDto(
        @Schema(description = "Tipo de logradouro", example = "Rua", required = true)
        @NotBlank(message = "O tipo de logradouro não pode ser vazio.")
        String end_tipo_logradouro,

        @Schema(description = "Logradouro", example = "Avenida Paulista", required = true)
        @NotBlank(message = "O logradouro não pode ser nulo ou vazio.")
        String end_logradouro,

        @Schema(description = "Número", example = "123", required = true)
        @NotNull(message = "O número não pode ser nulo ou vazio.")
        Long end_numero,

        @Schema(description = "Bairro", example = "Centro", required = true)
        @NotBlank(message = "O bairro não pode ser nulo ou vazio.")
        String end_bairro,

        @Schema(description = "ID da cidade", example = "1", required = true)
        @NotNull(message = "O ID da cidade não pode ser nulo ou vazio.")
        Long cidade_id
) {
    public Endereco fromDto(EnderecoRequestUpdateDto enderecoRequestDto) {
        Endereco endereco = new Endereco();
        endereco.setEnd_tipo_logradouro(enderecoRequestDto.end_tipo_logradouro());
        endereco.setEnd_logradouro(enderecoRequestDto.end_logradouro());
        endereco.setEnd_numero(enderecoRequestDto.end_numero());
        endereco.setEnd_bairro(enderecoRequestDto.end_bairro());
        return endereco;
    }

}
