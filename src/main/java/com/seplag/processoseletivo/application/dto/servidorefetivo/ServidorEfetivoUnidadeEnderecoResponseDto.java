package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

import java.util.List;


public record ServidorEfetivoUnidadeEnderecoResponseDto(
        String nomeServidor,
        List<UnidadeResponseDto> unidades
) {}