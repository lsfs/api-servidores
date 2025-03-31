package com.seplag.processoseletivo.application.dto.servidortemporario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ServidorTempResponseDto(
        Long servidorTempId,
        String servidorTempNome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataNascimento,
        String servidorTempSexo,
        String servidorTempMae,
        String servidorTempPai,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataAdmissao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate servidorTempDataDemissao,
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
