package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.FotoPessoaLinkResponse;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.fotopessoa.BuscaFotoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.fotopessoa.UploadFotoPessoaUseCase;
import com.seplag.processoseletivo.domain.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/foto-pessoa")
@Tag(name = "FotoPessoa")
public class FotoPessoaController {

    private final UploadFotoPessoaUseCase uploadFotoPessoaUseCase;
    private final BuscaFotoPorIdUseCase buscaFotoPorIdUseCase;

    public FotoPessoaController(UploadFotoPessoaUseCase uploadFotoPessoaUseCase, BuscaFotoPorIdUseCase buscaFotoPorIdUseCase) {
        this.uploadFotoPessoaUseCase = uploadFotoPessoaUseCase;
        this.buscaFotoPorIdUseCase = buscaFotoPorIdUseCase;
    }

    @Operation(summary = "Faz o upload de uma foto para uma pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Foto enviada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemRetorno.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping(value ="/{pessoaId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MensagemRetorno> uploadFoto(
        @Parameter(description = "ID da pessoa", required = true)
        @PathVariable Long pessoaId,
        @RequestParam("arquivo") MultipartFile arquivo
    ) {
        Pessoa pessoa = new Pessoa(pessoaId);
        uploadFotoPessoaUseCase.execute(pessoa, arquivo);
        System.out.println("Arquivo recebido: " + arquivo.getOriginalFilename());
        return new ResponseEntity<>(new MensagemRetorno("Arquivo enviado!"), HttpStatus.CREATED);
    }

    @Operation(summary = "Gera um link para a foto de uma pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link gerado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FotoPessoaLinkResponse.class))),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada", content = @Content)
    })
    @GetMapping("/{id}/link")
    public ResponseEntity<FotoPessoaLinkResponse> gerarLinkFoto(@PathVariable Long id) {
        var response = buscaFotoPorIdUseCase.execute(id);
        return new ResponseEntity<>(new FotoPessoaLinkResponse(response), HttpStatus.OK);
    }


    // :TODO : Retorno de unidade na busca por serviodores se nula
    // :TODO : Retorno de 404 na busca por foto de id nao existente
    // :TODO : Ordencao por id
    // :TODO: verificar dto de request de endereços (id cidade)

}