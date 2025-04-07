package com.seplag.processoseletivo.application.dto.shared;

import java.time.LocalDateTime;

public record MensagemErro(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public MensagemErro(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path);
    }
}
