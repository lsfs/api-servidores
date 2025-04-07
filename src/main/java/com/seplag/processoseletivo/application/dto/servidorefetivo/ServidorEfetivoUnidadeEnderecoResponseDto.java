package com.seplag.processoseletivo.application.dto.servidorefetivo;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO para resposta de unidade e endereço do servidor efetivo")
public record ServidorEfetivoUnidadeEnderecoResponseDto(
        @Schema(description = "Nome do servidor efetivo", example = "João Silva")
        String nomeServidor,

        @Schema(description = "Lista de unidades do servidor efetivo")
        List<UnidadeResponseDto> unidades
) {}