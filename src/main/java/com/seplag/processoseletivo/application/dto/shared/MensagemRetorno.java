package com.seplag.processoseletivo.application.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;

public record MensagemRetorno(
        @Schema(description = "Mensagem de retorno", example = "Mensagem genérica")
        String mensagem
) {
}
