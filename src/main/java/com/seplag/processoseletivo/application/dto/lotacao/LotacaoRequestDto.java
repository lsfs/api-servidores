package com.seplag.processoseletivo.application.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LotacaoRequestDto(
       Long pes_id,
       Long uni_id,
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  LocalDate lot_data_lotacao,
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate lot_data_remocao,
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
