package com.seplag.processoseletivo.application.dto.lotacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.pessoa.PessoaResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.infra.persistence.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.UnidadeMapper;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para resposta de lotação")
public record LotacaoResponseDto(
        @Schema(description = "ID da lotação", example = "1")
        Long lot_id,

        @Schema(description = "Pessoa associada à lotação")
        PessoaResponseDto pessoa,

        @Schema(description = "Unidade associada à lotação")
        UnidadeResponseDto unidade,

        @Schema(description = "Data de lotação", example = "2023-01-01")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate lot_data_lotacao,

        @Schema(description = "Data de remoção", example = "2023-12-31")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate lot_data_remocao,

        @Schema(description = "Portaria", example = "Portaria 123")
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