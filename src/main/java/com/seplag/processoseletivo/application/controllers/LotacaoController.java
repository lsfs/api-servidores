package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.AtualizarLotacaoUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscaLotacaoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.CriaLotacaoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lotacoes")
@RestController
public class LotacaoController {

    private final CriaLotacaoUseCase criaLotacaoUseCase;
    private final BuscaLotacaoPorIdUseCase buscaLotacaoUseCase;
    private final AtualizarLotacaoUseCase atualizarLotacaoUseCase;

    public LotacaoController(CriaLotacaoUseCase criaLotacaoUseCase, BuscaLotacaoPorIdUseCase buscaLotacaoUseCase, AtualizarLotacaoUseCase atualizarLotacaoUseCase) {
        this.criaLotacaoUseCase = criaLotacaoUseCase;
        this.buscaLotacaoUseCase = buscaLotacaoUseCase;
        this.atualizarLotacaoUseCase = atualizarLotacaoUseCase;
    }

    @PostMapping
    public ResponseEntity<LotacaoResponseDto> cria(
            @RequestBody LotacaoRequestDto lotacaoRequestDto
    ) {
        var response = this.criaLotacaoUseCase.execute(lotacaoRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoResponseDto> buscaPorId(
            @PathVariable Long id
    ) {
        var response = this.buscaLotacaoUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotacaoResponseDto> atualiza(
            @PathVariable Long id,
            @RequestBody LotacaoRequestDto lotacaoRequestDto
    ) {
        var response = this.atualizarLotacaoUseCase.execute(id, lotacaoRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
