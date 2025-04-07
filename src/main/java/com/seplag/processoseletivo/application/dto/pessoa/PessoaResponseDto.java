package com.seplag.processoseletivo.application.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seplag.processoseletivo.domain.model.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO para resposta de pessoa")
public record PessoaResponseDto (
        @Schema(description = "ID da pessoa", example = "1")
        Long pes_id,

        @Schema(description = "Nome da pessoa", example = "João Silva")
        String pes_nome,

        @Schema(description = "Data de nascimento", example = "1990-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate pes_data_nascimento,

        @Schema(description = "Sexo", example = "Masculino")
        String pes_sexo,

        @Schema(description = "Nome da mãe", example = "Maria Silva")
        String pes_mae,

        @Schema(description = "Nome do pai", example = "José Silva")
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
