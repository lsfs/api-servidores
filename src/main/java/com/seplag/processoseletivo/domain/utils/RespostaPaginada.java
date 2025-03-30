package com.seplag.processoseletivo.domain.utils;

import java.util.List;

public class RespostaPaginada<T> {

    private List<T> content;
    private int pageNumber;
    private int totalPages;
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
