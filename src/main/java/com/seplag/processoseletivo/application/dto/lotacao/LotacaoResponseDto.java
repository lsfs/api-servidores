package com.seplag.processoseletivo.application.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.pessoa.PessoaResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.infra.persistence.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.UnidadeMapper;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LotacaoResponseDto(
        Long lot_id,
        PessoaResponseDto pessoa,
        UnidadeResponseDto unidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate lot_data_lotacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate lot_data_remocao,
        String lot_portaria
        ) {

    public static LotacaoResponseDto of(Lotacao lotacao) {
        return new LotacaoResponseDto(
                lotacao.getLot_id(),
                PessoaResponseDto.of(lotacao.getPessoa()),
                UnidadeResponseDto.simpleDetailsOf(lotacao.getUnidade()),
                lotacao.getLot_data_lotacao(),
                lotacao.getLot_data_remocao(),
                lotacao.getLot_portaria()
        );
    }

}
