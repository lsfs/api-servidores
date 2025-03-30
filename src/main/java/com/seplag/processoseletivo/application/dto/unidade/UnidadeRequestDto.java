package com.seplag.processoseletivo.application.dto.unidade;


public record UnidadeRequestDto(
        String unid_nome,
        String unid_sigla
) {

    public UnidadeRequestDto {
        if (unid_nome == null || unid_nome.isBlank()) {
            throw new IllegalArgumentException("Nome da unidade não pode ser nulo ou vazio");
        }
        if (unid_sigla == null || unid_sigla.isBlank()) {
            throw new IllegalArgumentException("Sigla da unidade não pode ser nula ou vazia");
        }
    }

}
