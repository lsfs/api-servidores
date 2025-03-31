package com.seplag.processoseletivo.application.dto.servidortemporario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ServidorTempRequestDto(
        String servidorTempNome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataNascimento,
        String servidorTempSexo,
        String servidorTempMae,
        String servidorTempPai,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataAdmissao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataDemissao,
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
