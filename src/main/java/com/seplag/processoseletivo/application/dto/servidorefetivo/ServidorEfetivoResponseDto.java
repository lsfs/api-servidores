package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record ServidorEfetivoResponseDto(
        Long servidorEfetivoId,
        String servidorEfetivoNome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorEfetivoDataNascimento,
        String servidorEfetivoSexo,
        String servidorEfetivoMae,
        String servidorEfetivoPai,
        String servidorMatricula,
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
