package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.fotopessoa.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO para resposta de detalhes do servidor efetivo")
public record ServidorEfetivoDetailsResponseDto(
        @Schema(description = "Nome do servidor", example = "João Silva")
        String nome,

        @Schema(description = "Idade do servidor", example = "35")
        int idade,

        @Schema(description = "Unidade de lotação do servidor")
        UnidadeResponseDto unidadeLotacao,

        @Schema(description = "Lista de fotos do servidor")
        List<FotoPessoaLinkResponse> fotos
) {
}