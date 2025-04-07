package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.*;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidor-efetivo")
@Tag(name = "ServidorEfetivo", description = "Operações relacionadas a servidores efetivos")
@Validated
public class ServidorEfetivoController {

    private final CriarServidorEfetivoUseCase criarServidorEfetivoUseCase;
    private final AtualizarServidorEfetivoUseCase atualizarServidorEfetivoUseCase;
    private final BuscarServidoresEfetivosUseCase buscarServidorEfetivosUseCase;
    private final DeletarServidorEfetivoUseCase deletarServidorEfetivoUseCase;
    private final BuscarServidorEfetivoPorIdUseCase buscarServidoresEfetivosUseCase;
    private final BuscarServidorEfetivoPorUnidadeUseCase buscarServidorEfetivoPorUnidadeUseCase;


    public ServidorEfetivoController(CriarServidorEfetivoUseCase criarServidorEfetivoUseCase, AtualizarServidorEfetivoUseCase atualizarServidorEfetivoUseCase, BuscarServidoresEfetivosUseCase buscarServidorEfetivosUseCase, DeletarServidorEfetivoUseCase deletarServidorEfetivoUseCase, BuscarServidorEfetivoPorIdUseCase buscarServidoresEfetivosUseCase, BuscarServidorEfetivoPorUnidadeUseCase buscarServidorEfetivoPorUnidadeUseCase) {
        this.criarServidorEfetivoUseCase = criarServidorEfetivoUseCase;
        this.atualizarServidorEfetivoUseCase = atualizarServidorEfetivoUseCase;
        this.buscarServidorEfetivosUseCase = buscarServidorEfetivosUseCase;
        this.deletarServidorEfetivoUseCase = deletarServidorEfetivoUseCase;
        this.buscarServidoresEfetivosUseCase = buscarServidoresEfetivosUseCase;
        this.buscarServidorEfetivoPorUnidadeUseCase = buscarServidorEfetivoPorUnidadeUseCase;
    }

    @Operation(summary = "Cria um novo servidor efetivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servidor efetivo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorEfetivoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ServidorEfetivoResponseDto> criar(
            @Valid @RequestBody ServidorEfetivoRequestDto servidorEfetivoRequestDto
    ) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = criarServidorEfetivoUseCase.execute(servidorEfetivoRequestDto);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca todos os servidores efetivos com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidores efetivos encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping
    public ResponseEntity<RespostaPaginada<ServidorEfetivoResponseDto>> buscarServidoresEfetivos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorEfetivoResponseDto> resposta = buscarServidorEfetivosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Busca um servidor efetivo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorEfetivoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDto> buscarPorId(@PathVariable Long id) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = buscarServidoresEfetivosUseCase.execute(id);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um servidor efetivo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorEfetivoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServidorEfetivoRequestDto servidorEfetivoRequestDto
    ) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = atualizarServidorEfetivoUseCase.execute(id, servidorEfetivoRequestDto);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Deleta um servidor efetivo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor efetivo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarServidorEfetivoUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Busca servidores efetivos por unidade com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidores efetivos encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping("/unidade/{unid_id}")
    public ResponseEntity<RespostaPaginada<ServidorEfetivoDetailsResponseDto>> buscarPorUnidade(
            @PathVariable(name = "unid_id") Long unid_id,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorEfetivoDetailsResponseDto> resposta = buscarServidorEfetivoPorUnidadeUseCase.execute(unid_id, pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}