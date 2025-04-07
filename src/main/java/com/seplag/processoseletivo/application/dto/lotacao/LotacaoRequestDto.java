package com.seplag.processoseletivo.application.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO para requisição de lotação")
public record LotacaoRequestDto(
        @Schema(description = "ID da pessoa", example = "1", required = true)
        Long pes_id,

        @Schema(description = "ID da unidade", example = "2", required = true)
        Long uni_id,

        @Schema(description = "Data de lotação", example = "2023-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate lot_data_lotacao,

        @Schema(description = "Data de remoção", example = "2023-12-31")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate lot_data_remocao,

        @Schema(description = "Portaria", example = "Portaria 123", required = true)
        String lot_portaria
) {

    public LotacaoRequestDto {
        if (pes_id == null) {
            throw new IllegalArgumentException("Id da pessoa não pode ser nulo");
        }
        if (uni_id == null) {
            throw new IllegalArgumentException("Id da unidade não pode ser nulo");
        }
        if (lot_data_lotacao == null) {
            throw new IllegalArgumentException("Data de lotação não pode ser nula");
        }
        if (lot_portaria == null || lot_portaria.isBlank()) {
            throw new IllegalArgumentException("Portaria não pode ser nula ou vazia");
        }
    }
}