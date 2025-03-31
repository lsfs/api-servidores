package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record ServidorEfetivoRequestDto (
        String servidorEfetivoNome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorEfetivoDataNascimento,
        String servidorEfetivoSexo,
        String servidorEfetivoMae,
        String servidorEfetivoPai,
        String servidorMatricula,
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
