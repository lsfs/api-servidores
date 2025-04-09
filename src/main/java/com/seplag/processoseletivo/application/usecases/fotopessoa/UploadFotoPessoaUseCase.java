package com.seplag.processoseletivo.application.usecases.fotopessoa;

import com.seplag.processoseletivo.application.dto.fotopessoa.FotoPessoaCreateResponseDto;
import com.seplag.processoseletivo.domain.model.Pessoa;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFotoPessoaUseCase {
    FotoPessoaCreateResponseDto execute(Pessoa pessoa, List<MultipartFile> file);
}
