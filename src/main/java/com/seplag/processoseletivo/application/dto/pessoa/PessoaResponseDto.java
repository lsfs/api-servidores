package com.seplag.processoseletivo.application.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seplag.processoseletivo.domain.model.Pessoa;

import java.time.LocalDate;

public record PessoaResponseDto (
        Long pes_id,
        String pes_nome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate pes_data_nascimento,
        String pes_sexo,
        String pes_mae,
        String pes_pai
) {

    public static PessoaResponseDto of(Pessoa pessoa) {
        return new PessoaResponseDto(
                pessoa.getPes_id(),
                pessoa.getPes_nome(),
                pessoa.getPes_data_nascimento(),
                pessoa.getPes_sexo(),
                pessoa.getPes_mae(),
                pessoa.getPes_pai()
        );
    }
}
