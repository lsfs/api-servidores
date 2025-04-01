package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.AtualizarLotacaoUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.CriaLotacaoUseCase;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lotacoes")
@RestController
public class LotacaoController {

    private final CriaLotacaoUseCase criaLotacaoUseCase;
    private final BuscarLotacaoPorIdUseCase buscarLotacaoPorIdUseCase;
    private final AtualizarLotacaoUseCase atualizarLotacaoUseCase;
    private final BuscarLotacaoUseCase buscarLotacaoUseCase;

    public LotacaoController(CriaLotacaoUseCase criaLotacaoUseCase, BuscarLotacaoPorIdUseCase buscaLotacaoUseCase, AtualizarLotacaoUseCase atualizarLotacaoUseCase, BuscarLotacaoUseCase buscarLotacaoUseCase) {
        this.criaLotacaoUseCase = criaLotacaoUseCase;
        this.buscarLotacaoPorIdUseCase = buscaLotacaoUseCase;
        this.atualizarLotacaoUseCase = atualizarLotacaoUseCase;
        this.buscarLotacaoUseCase = buscarLotacaoUseCase;
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
        var response = this.buscarLotacaoPorIdUseCase.execute(id);
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

    @GetMapping
    public ResponseEntity<RespostaPaginada<LotacaoResponseDto>> buscaTodos(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") int tamanho
    ) {
        var response = this.buscarLotacaoUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
