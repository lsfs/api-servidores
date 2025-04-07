package com.seplag.processoseletivo.domain.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Classe para resposta paginada")
public class RespostaPaginada<T> {

    @Schema(description = "Conteúdo da página", required = true)
    private List<T> content;

    @Schema(description = "Número da página", example = "1", required = true)
    private int pageNumber;

    @Schema(description = "Total de páginas", example = "10", required = true)
    private int totalPages;

    @Schema(description = "Total de elementos", example = "100", required = true)
    private long totalElements;

    public RespostaPaginada(List<T> content, int pageNumber, int totalPages, long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}