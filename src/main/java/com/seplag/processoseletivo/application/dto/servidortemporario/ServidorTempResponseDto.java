package com.seplag.processoseletivo.application.dto.servidortemporario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para resposta de servidor temporário")
public record ServidorTempResponseDto(
        @Schema(description = "ID do servidor temporário", example = "1")
        Long servidorTempId,

        @Schema(description = "Nome do servidor temporário", example = "João Silva")
        String servidorTempNome,

        @Schema(description = "Data de nascimento do servidor temporário", example = "1990-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataNascimento,

        @Schema(description = "Sexo do servidor temporário", example = "Masculino")
        String servidorTempSexo,

        @Schema(description = "Nome da mãe do servidor temporário", example = "Maria Silva")
        String servidorTempMae,

        @Schema(description = "Nome do pai do servidor temporário", example = "José Silva")
        String servidorTempPai,

        @Schema(description = "Data de admissão do servidor temporário", example = "2020-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataAdmissao,

        @Schema(description = "Data de demissão do servidor temporário", example = "2021-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataDemissao,

        @Schema(description = "Endereços do servidor temporário")
        Set<EnderecoResponseDto> servidorTempEnderecos
) {


    public static ServidorTempResponseDto of(ServidorTemporario servidorTemporario) {
        return new ServidorTempResponseDto(
                servidorTemporario.getPessoa().getPes_id(),
                servidorTemporario.getPessoa().getPes_nome(),
                servidorTemporario.getPessoa().getPes_data_nascimento(),
                servidorTemporario.getPessoa().getPes_sexo(),
                servidorTemporario.getPessoa().getPes_mae(),
                servidorTemporario.getPessoa().getPes_pai(),
                servidorTemporario.getSt_data_admissao(),
                servidorTemporario.getSt_data_demissao(),
                servidorTemporario.getPessoa().getEnderecos().stream()
                        .map(EnderecoResponseDto::of)
                        .collect(Collectors.toSet())
        );
    }
}
