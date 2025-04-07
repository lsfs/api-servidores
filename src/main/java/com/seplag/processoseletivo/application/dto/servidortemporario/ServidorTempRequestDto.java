package com.seplag.processoseletivo.application.dto.servidortemporario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para requisição de servidor temporário")
public record ServidorTempRequestDto(
        @Schema(description = "Nome do servidor temporário", example = "João Silva", required = true)
        String servidorTempNome,

        @Schema(description = "Data de nascimento do servidor temporário", example = "1990-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataNascimento,

        @Schema(description = "Sexo do servidor temporário", example = "Masculino", required = true)
        String servidorTempSexo,

        @Schema(description = "Nome da mãe do servidor temporário", example = "Maria Silva", required = true)
        String servidorTempMae,

        @Schema(description = "Nome do pai do servidor temporário", example = "José Silva", required = true)
        String servidorTempPai,

        @Schema(description = "Data de admissão do servidor temporário", example = "2020-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataAdmissao,

        @Schema(description = "Data de demissão do servidor temporário", example = "2021-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorTempDataDemissao,

        @Schema(description = "Endereços do servidor temporário", required = true)
        Set<Long> servidorTempEnderecos
) {

    public ServidorTempRequestDto {
        if (servidorTempNome == null || servidorTempNome.isBlank()) {
            throw new IllegalArgumentException("Nome do servidor temporário não pode ser nulo ou vazio");
        }
        if (servidorTempDataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento do servidor temporário não pode ser nula ou vazia");
        }

        if (servidorTempDataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento do servidor temporário não pode ser futura");
        }

        if (servidorTempDataAdmissao != null && servidorTempDataAdmissao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de admissão do servidor temporário não pode ser futura");
        }

        if (servidorTempSexo == null || servidorTempSexo.isBlank()) {
            throw new IllegalArgumentException("Sexo do servidor temporário não pode ser nulo ou vazio");
        }
        if (servidorTempMae == null || servidorTempMae.isBlank()) {
            throw new IllegalArgumentException("Nome da mãe do servidor temporário não pode ser nulo ou vazio");
        }
        if (servidorTempPai == null || servidorTempPai.isBlank()) {
            throw new IllegalArgumentException("Nome do pai do servidor temporário não pode ser nulo ou vazio");
        }
        if (servidorTempDataAdmissao == null) {
            throw new IllegalArgumentException("Data de admissão do servidor temporário não pode ser nula ou vazia");
        }

        if (servidorTempDataDemissao != null && servidorTempDataDemissao.isBefore(servidorTempDataAdmissao) ) {
            throw new IllegalArgumentException("Data de demissão do servidor temporário não pode ser anterior à data de admissão");
        }

        if (servidorTempEnderecos == null || servidorTempEnderecos.isEmpty()) {
            throw new IllegalArgumentException("Endereços do servidor temporário não podem ser nulos ou vazios");
        }

    }

}
