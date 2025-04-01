package com.seplag.processoseletivo.domain.services;


import com.seplag.processoseletivo.domain.dto.FotoPessoaUpload;

import java.io.InputStream;

public interface FotoStorageService {
    void salvar(FotoPessoaUpload foto);
    void remover(String nomeArquivo);
    String gerarLinkTemporario(String nomeArquivo);
}
