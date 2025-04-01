package com.seplag.processoseletivo.application.usecases.fotopessoa.impl;

import com.seplag.processoseletivo.application.usecases.fotopessoa.UploadFotoPessoaUseCase;
import com.seplag.processoseletivo.domain.dto.FotoPessoaUpload;
import com.seplag.processoseletivo.domain.model.FotoPessoa;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.repositories.FotoPessoaRepository;
import com.seplag.processoseletivo.domain.services.FotoStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFotoPessoaUseCaseImpl implements UploadFotoPessoaUseCase {

    private final FotoPessoaRepository fotoRepository;
    private final FotoStorageService storageService;

    public UploadFotoPessoaUseCaseImpl(FotoPessoaRepository fotoRepository,
                                   FotoStorageService storageService) {
        this.fotoRepository = fotoRepository;
        this.storageService = storageService;
    }


    public void execute(Pessoa pessoa, MultipartFile arquivo) {
        try {
            String nomeArquivo = UUID.randomUUID().toString();


            FotoPessoaUpload upload = new FotoPessoaUpload(
                    nomeArquivo,
                    arquivo.getContentType(),
                    arquivo.getInputStream()
            );

            storageService.salvar(upload);

            FotoPessoa foto = new FotoPessoa(pessoa, "pessoas", nomeArquivo);
            fotoRepository.salvar(foto);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar upload de foto", e);
        }
    }
}
