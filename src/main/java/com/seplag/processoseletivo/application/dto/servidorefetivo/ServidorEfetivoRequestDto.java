package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "DTO para requisição de servidor efetivo")
public record ServidorEfetivoRequestDto (
        @Schema(description = "Nome do servidor efetivo", example = "João Silva", required = true)
        @NotBlank(message = "O nome do servidor efetivo não pode ser nulo ou vazio.")
        String servidorEfetivoNome,

        @Schema(description = "Data de nascimento do servidor efetivo", example = "1990-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "A data de nascimento do servidor efetivo não pode ser nula.")
        LocalDate servidorEfetivoDataNascimento,

        @Schema(description = "Sexo do servidor efetivo", example = "Masculino", required = true)
        @NotBlank(message = "O sexo do servidor efetivo não pode ser nulo ou vazio.")
        String servidorEfetivoSexo,

        @Schema(description = "Nome da mãe do servidor efetivo", example = "Maria Silva", required = true)
        @NotBlank(message = "O nome da mãe do servidor efetivo não pode ser nulo ou vazio.")
        String servidorEfetivoMae,

        @Schema(description = "Nome do pai do servidor efetivo", example = "José Silva", required = true)
        @NotBlank(message = "O nome do pai do servidor efetivo não pode ser nulo ou vazio.")
        String servidorEfetivoPai,

        @Schema(description = "Matrícula do servidor efetivo", example = "123456", required = true)
        @NotBlank(message = "A matrícula do servidor efetivo não pode ser nula ou vazia.")
        String servidorMatricula,

        @Schema(description = "Endereços do servidor efetivo", required = true)
        @NotEmpty(message = "Os endereços do servidor efetivo não podem ser nulos ou vazios.")
        Set<Long> servidorEfetivoEnderecos
) {
}
