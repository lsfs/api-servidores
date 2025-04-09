package com.seplag.processoseletivo.application.usecases.fotopessoa.impl;

import com.seplag.processoseletivo.application.dto.fotopessoa.FotoPessoaCreateResponseDto;
import com.seplag.processoseletivo.application.dto.fotopessoa.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.usecases.fotopessoa.UploadFotoPessoaUseCase;
import com.seplag.processoseletivo.domain.dto.FotoPessoaUpload;
import com.seplag.processoseletivo.domain.model.FotoPessoa;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.repositories.FotoPessoaRepository;
import com.seplag.processoseletivo.domain.services.FotoStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UploadFotoPessoaUseCaseImpl implements UploadFotoPessoaUseCase {

    private final FotoPessoaRepository fotoRepository;
    private final FotoStorageService storageService;

    public UploadFotoPessoaUseCaseImpl(FotoPessoaRepository fotoRepository,
                                   FotoStorageService storageService) {
        this.fotoRepository = fotoRepository;
        this.storageService = storageService;
    }


    public FotoPessoaCreateResponseDto execute(Pessoa pessoa, List<MultipartFile> arquivos) {
        List<FotoPessoaLinkResponse> arquivosSalvos = new ArrayList<>();

        arquivos.forEach(arquivo -> {
            FotoPessoaUpload upload;
            try {
                String nomeArquivo = this.gerarNome(arquivo);
                upload = new FotoPessoaUpload(
                        nomeArquivo,
                        arquivo.getContentType(),
                        arquivo.getInputStream()
                );
                storageService.salvar(upload);

                FotoPessoa foto = new FotoPessoa(pessoa, "pessoas", nomeArquivo);
                FotoPessoa fotoSalva = fotoRepository.salvar(foto);
                arquivosSalvos.add(
                        new FotoPessoaLinkResponse(
                                storageService.gerarLinkTemporario(fotoSalva.getFp_hash())
                        )
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return new FotoPessoaCreateResponseDto(
                pessoa.getPes_id(),
                arquivosSalvos
        );
    }

    private String gerarNome(MultipartFile file) throws Exception {


        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(file.getBytes());


        String hash = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);

        String nomeOriginal = file.getOriginalFilename();
        String extensao = "";

        if (nomeOriginal != null && nomeOriginal.contains(".")) {
            extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
        }

        int maxHashLength = 50 - extensao.length();
        return hash.substring(0, Math.min(hash.length(), maxHashLength)) + extensao;
    }

}
