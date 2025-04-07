package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "DTO para requisição de servidor efetivo")
public record ServidorEfetivoRequestDto (
        @Schema(description = "Nome do servidor efetivo", example = "João Silva", required = true)
        String servidorEfetivoNome,

        @Schema(description = "Data de nascimento do servidor efetivo", example = "1990-01-01", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate servidorEfetivoDataNascimento,

        @Schema(description = "Sexo do servidor efetivo", example = "Masculino", required = true)
        String servidorEfetivoSexo,

        @Schema(description = "Nome da mãe do servidor efetivo", example = "Maria Silva", required = true)
        String servidorEfetivoMae,

        @Schema(description = "Nome do pai do servidor efetivo", example = "José Silva", required = true)
        String servidorEfetivoPai,

        @Schema(description = "Matrícula do servidor efetivo", example = "123456", required = true)
        String servidorMatricula,

        @Schema(description = "Endereços do servidor efetivo", required = true)
        Set<Long> servidorEfetivoEnderecos
) {

    public ServidorEfetivoRequestDto {
        if (servidorEfetivoNome == null || servidorEfetivoNome.isBlank()) {
            throw new IllegalArgumentException("Nome do servidor efetivo não pode ser nulo ou vazio");
        }
        if (servidorEfetivoDataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento do servidor efetivo não pode ser nula ou vazia");
        }

        if (servidorEfetivoDataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento do servidor efetivo não pode ser futura");
        }

        if (servidorEfetivoSexo == null || servidorEfetivoSexo.isBlank()) {
            throw new IllegalArgumentException("Sexo do servidor efetivo não pode ser nulo ou vazio");
        }
        if (servidorEfetivoMae == null || servidorEfetivoMae.isBlank()) {
            throw new IllegalArgumentException("Nome da mãe do servidor efetivo não pode ser nulo ou vazio");
        }
        if (servidorEfetivoPai == null || servidorEfetivoPai.isBlank()) {
            throw new IllegalArgumentException("Nome do pai do servidor efetivo não pode ser nulo ou vazio");
        }
        if (servidorMatricula == null || servidorMatricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula do servidor efetivo não pode ser nula ou vazia");
        }

        if (servidorEfetivoEnderecos == null || servidorEfetivoEnderecos.isEmpty()) {
            throw new IllegalArgumentException("Endereços do servidor efetivo não podem ser nulos ou vazios");
        }

    }
}
