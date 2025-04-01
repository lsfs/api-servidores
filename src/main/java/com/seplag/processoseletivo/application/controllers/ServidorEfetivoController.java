package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoDetailsResponseDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.*;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidor-efetivo")
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

    @PostMapping
    public ResponseEntity<ServidorEfetivoResponseDto> criar(
            @RequestBody ServidorEfetivoRequestDto servidorEfetivoRequestDto
    ) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = criarServidorEfetivoUseCase.execute(servidorEfetivoRequestDto);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RespostaPaginada<ServidorEfetivoResponseDto>> buscarServidoresEfetivos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorEfetivoResponseDto> resposta = buscarServidorEfetivosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDto> buscarPorId(@PathVariable Long id) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = buscarServidoresEfetivosUseCase.execute(id);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody ServidorEfetivoRequestDto servidorEfetivoRequestDto
    ) {
        ServidorEfetivoResponseDto servidorEfetivoResponseDto = atualizarServidorEfetivoUseCase.execute(id, servidorEfetivoRequestDto);
        return new ResponseEntity<>(servidorEfetivoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarServidorEfetivoUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/unidade/{idUnidade}")
    public ResponseEntity<RespostaPaginada<ServidorEfetivoDetailsResponseDto>> buscarPorUnidade(
            @PathVariable Long idUnidade,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorEfetivoDetailsResponseDto> resposta = buscarServidorEfetivoPorUnidadeUseCase.execute(idUnidade, pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
