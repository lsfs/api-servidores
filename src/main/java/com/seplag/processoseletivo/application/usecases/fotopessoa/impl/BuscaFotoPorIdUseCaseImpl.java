package com.seplag.processoseletivo.application.usecases.fotopessoa.impl;

import com.seplag.processoseletivo.application.usecases.fotopessoa.BuscaFotoPorIdUseCase;
import com.seplag.processoseletivo.domain.repositories.FotoPessoaRepository;
import com.seplag.processoseletivo.domain.services.FotoStorageService;
import org.springframework.stereotype.Service;

@Service
public class BuscaFotoPorIdUseCaseImpl implements BuscaFotoPorIdUseCase {

    private final FotoPessoaRepository fotoRepository;
    private final FotoStorageService storageService;

    public BuscaFotoPorIdUseCaseImpl(FotoPessoaRepository fotoRepository, FotoStorageService storageService) {
        this.fotoRepository = fotoRepository;
        this.storageService = storageService;
    }


    @Override
    public String execute(Long id) {

        var foto = fotoRepository.buscarPorId(id);

        return foto.map(fotoPessoa -> storageService.gerarLinkTemporario(fotoPessoa.getFp_hash())).orElse(null);

    }
}
