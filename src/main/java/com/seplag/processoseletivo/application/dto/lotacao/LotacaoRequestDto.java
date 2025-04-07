package com.seplag.processoseletivo.application.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "DTO para requisição de lotação")
public record LotacaoRequestDto(
        @Schema(description = "ID da pessoa", example = "1", required = true)
        @NotNull(message = "O ID da pessoa não pode ser nulo ou vazio.")
        Long pes_id,

        @Schema(description = "ID da unidade", example = "2", required = true)
        @NotNull(message = "O ID da unidade não pode ser nulo ou vazio.")
        Long uni_id,

        @Schema(description = "Data de lotação", example = "2023-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de lotação não pode ser nula.")
        LocalDate lot_data_lotacao,

        @Schema(description = "Data de remoção", example = "2023-12-31")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate lot_data_remocao,

        @Schema(description = "Portaria", example = "Portaria 123", required = true)
        @NotBlank(message = "A portaria não pode ser nula ou vazia.")
        String lot_portaria
) {
}