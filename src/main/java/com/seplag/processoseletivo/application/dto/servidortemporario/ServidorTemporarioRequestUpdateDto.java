package com.seplag.processoseletivo.application.dto.servidortemporario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para requisição de servidor temporário")
public record ServidorTemporarioRequestUpdateDto(
        @Schema(description = "Nome do servidor temporário", example = "João Silva", required = true)
        @NotBlank(message = "O nome do servidor temporário não pode ser nulo ou vazio.")
        String servidorTempNome,

        @Schema(description = "Data de nascimento do servidor temporário", example = "1990-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de nascimento do servidor temporário não pode ser nula.")
        @Past(message = "A da de nascimento deve ser anterior à data atual.")
        LocalDate servidorTempDataNascimento,

        @Schema(description = "Sexo do servidor temporário", example = "Masculino", required = true)
        @NotBlank(message = "O sexo do servidor temporário não pode ser nulo ou vazio.")
        String servidorTempSexo,

        @Schema(description = "Nome da mãe do servidor temporário", example = "Maria Silva", required = true)
        @NotBlank(message = "O nome da mãe do servidor temporário não pode ser nulo ou vazio.")
        String servidorTempMae,

        @Schema(description = "Nome do pai do servidor temporário", example = "José Silva", required = true)
        @NotBlank(message = "O nome do pai do servidor temporário não pode ser nulo ou vazio.")
        String servidorTempPai,

        @Schema(description = "Data de admissão do servidor temporário", example = "2020-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de admissão do servidor temporário não pode ser nula.")
        LocalDate servidorTempDataAdmissao,

        @Schema(description = "Data de demissão do servidor temporário", example = "2021-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataDemissao,

        @Schema(description = "Endereços do servidor temporário", required = true)
        @NotEmpty(message = "Pelo menos um endereço deve ser informado.")
        Set<Long> servidorTempEnderecos
) {
}
