package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.AtualizarCidadeUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.CriarCidadeUseCase;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
@Tag(name = "Cidade")
public class CidadeController {

    private final CriarCidadeUseCase criarCidadeUseCase;
    private final BuscarCidadesPorIdUseCase buscarCidadesPorIdUseCase;
    private final BuscarCidadesUseCase buscarCidadesUseCase;
    private final AtualizarCidadeUseCase atualizarCidadeUseCase;

    public CidadeController(CriarCidadeUseCase criarCidadeUseCase, BuscarCidadesPorIdUseCase buscarCidadesPorIdUseCase, BuscarCidadesUseCase buscarCidadesUseCase, AtualizarCidadeUseCase atualizarCidadeUseCase) {
        this.criarCidadeUseCase = criarCidadeUseCase;
        this.buscarCidadesPorIdUseCase = buscarCidadesPorIdUseCase;
        this.buscarCidadesUseCase = buscarCidadesUseCase;
        this.atualizarCidadeUseCase = atualizarCidadeUseCase;
    }

    @Operation(summary = "Cria uma nova cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cidade criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CidadeResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CidadeResponseDto> criarCidade(
            @RequestBody CidadeRequestDto cidadeRequestDto
    ) {
        var response = this.criarCidadeUseCase.execute(cidadeRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca uma cidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CidadeResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDto> buscarCidadePorId(
            @PathVariable Long id
    ) {
        var response = this.buscarCidadesPorIdUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Busca todas as cidades com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidades encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping
    public ResponseEntity<RespostaPaginada<CidadeResponseDto>> buscarCidades(
            @RequestParam(value = "page", defaultValue = "0") int pagina,
            @RequestParam(value = "size", defaultValue = "10") int tamanho
    ) {
        var respostaPaginada = this.buscarCidadesUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma cidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CidadeResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDto> atualizarCidade(
            @PathVariable Long id,
            @RequestBody CidadeRequestDto cidadeRequestDto
    ) {
        var response = this.atualizarCidadeUseCase.execute(id, cidadeRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}