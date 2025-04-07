package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "DTO para resposta de servidor efetivo")
public record ServidorEfetivoResponseDto(
        @Schema(description = "ID do servidor efetivo", example = "1")
        Long servidorEfetivoId,

        @Schema(description = "Nome do servidor efetivo", example = "João Silva")
        String servidorEfetivoNome,

        @Schema(description = "Data de nascimento do servidor efetivo", example = "1990-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorEfetivoDataNascimento,

        @Schema(description = "Sexo do servidor efetivo", example = "Masculino")
        String servidorEfetivoSexo,

        @Schema(description = "Nome da mãe do servidor efetivo", example = "Maria Silva")
        String servidorEfetivoMae,

        @Schema(description = "Nome do pai do servidor efetivo", example = "José Silva")
        String servidorEfetivoPai,

        @Schema(description = "Matrícula do servidor efetivo", example = "123456")
        String servidorMatricula,

        @Schema(description = "Endereços do servidor efetivo")
        Set<EnderecoResponseDto> servidorEfetivoEnderecos
) {

    public static ServidorEfetivoResponseDto of(com.seplag.processoseletivo.domain.model.ServidorEfetivo servidorEfetivo) {
        return new ServidorEfetivoResponseDto(
                servidorEfetivo.getPessoa().getPes_id(),
                servidorEfetivo.getPessoa().getPes_nome(),
                servidorEfetivo.getPessoa().getPes_data_nascimento(),
                servidorEfetivo.getPessoa().getPes_sexo(),
                servidorEfetivo.getPessoa().getPes_mae(),
                servidorEfetivo.getPessoa().getPes_pai(),
                servidorEfetivo.getSe_matricula(),
                servidorEfetivo.getPessoa().getEnderecos().stream()
                        .map(EnderecoResponseDto::of)
                        .collect(Collectors.toSet())
        );
    }

}
