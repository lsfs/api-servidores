package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.fotopessoa.BuscaFotoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.fotopessoa.UploadFotoPessoaUseCase;
import com.seplag.processoseletivo.domain.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/foto-pessoa")
public class FotoPessoaController {

    private final UploadFotoPessoaUseCase uploadFotoPessoaUseCase;
    private final BuscaFotoPorIdUseCase buscaFotoPorIdUseCase;

    public FotoPessoaController(UploadFotoPessoaUseCase uploadFotoPessoaUseCase, BuscaFotoPorIdUseCase buscaFotoPorIdUseCase) {
        this.uploadFotoPessoaUseCase = uploadFotoPessoaUseCase;
        this.buscaFotoPorIdUseCase = buscaFotoPorIdUseCase;
    }

    @PostMapping("/{pessoaId}/foto")
    public ResponseEntity<MensagemRetorno> uploadFoto(
        @PathVariable Long pessoaId,
        @RequestParam("arquivo") MultipartFile arquivo
    ) {
        Pessoa pessoa = new Pessoa(pessoaId);
        uploadFotoPessoaUseCase.execute(pessoa, arquivo);
        System.out.println("Arquivo recebido: " + arquivo.getOriginalFilename());
        return new ResponseEntity<>(new MensagemRetorno("Arquivo enviado!"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/link")
    public ResponseEntity<FotoPessoaLinkResponse> gerarLinkFoto(@PathVariable Long id) {
        var response = buscaFotoPorIdUseCase.execute(id);
        return new ResponseEntity<>(new FotoPessoaLinkResponse(response), HttpStatus.OK);
    }

}
