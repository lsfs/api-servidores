package com.seplag.processoseletivo.application.usecases.fotopessoa;

import com.seplag.processoseletivo.domain.model.Pessoa;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFotoPessoaUseCase {
    void execute(Pessoa pessoa, MultipartFile file);
}
