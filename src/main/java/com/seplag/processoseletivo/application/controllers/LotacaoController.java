package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.lotacao.LotacaoRequestDto;
import com.seplag.processoseletivo.application.dto.lotacao.LotacaoResponseDto;
import com.seplag.processoseletivo.application.usecases.lotacao.AtualizarLotacaoUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.BuscarLotacaoUseCase;
import com.seplag.processoseletivo.application.usecases.lotacao.CriaLotacaoUseCase;
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

@RequestMapping("/lotacoes")
@RestController
@Tag(name = "Lotação", description = "Operações relacionadas a lotações")
@Validated
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

    @Operation(summary = "Cria uma nova lotação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lotação criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LotacaoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LotacaoResponseDto> cria(
            @Valid @RequestBody LotacaoRequestDto lotacaoRequestDto
    ) {
        var response = this.criaLotacaoUseCase.execute(lotacaoRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca uma lotação pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotação encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LotacaoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LotacaoResponseDto> buscaPorId(
            @PathVariable Long id
    ) {
        var response = this.buscarLotacaoPorIdUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma lotação pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotação atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LotacaoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<LotacaoResponseDto> atualiza(
            @PathVariable Long id,
            @Valid @RequestBody LotacaoRequestDto lotacaoRequestDto
    ) {
        var response = this.atualizarLotacaoUseCase.execute(id, lotacaoRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Busca todas as lotações com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotações encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping
    public ResponseEntity<RespostaPaginada<LotacaoResponseDto>> buscaTodos(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") int tamanho
    ) {
        var response = this.buscarLotacaoUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}