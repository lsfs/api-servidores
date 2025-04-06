package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seplag.processoseletivo.application.dto.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ServidorEfetivoDetailsResponseDto(
        String nome,
        int idade,
        UnidadeResponseDto unidadeLotacao,
        List<FotoPessoaLinkResponse> fotos
) {
}
