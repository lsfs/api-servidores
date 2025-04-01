package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;

import java.util.List;
import java.util.Set;

public record ServidorEfetivoUnidadeEnderecoResponseDto(
        String nomeServidor,
        List<UnidadeResponseDto> unidades
) {}