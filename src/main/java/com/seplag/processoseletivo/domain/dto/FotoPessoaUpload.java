package com.seplag.processoseletivo.domain.dto;

import java.io.InputStream;

public record FotoPessoaUpload(String nomeArquivo, String contentType, InputStream inputStream) {}