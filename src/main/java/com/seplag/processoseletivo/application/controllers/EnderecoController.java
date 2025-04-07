package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.endereco.AtualizarEnderecoUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.BuscarEnderecoPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.BuscarEnderecosUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.CriarEnderecoUseCase;
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
@RequestMapping("/enderecos")
@Tag(name = "Endereço")
@Validated
public class EnderecoController {

    private final CriarEnderecoUseCase criarEnderecoUseCase;
    private final BuscarEnderecosUseCase buscarEnderecosUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    private final BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;

    public EnderecoController(CriarEnderecoUseCase criarEnderecoUseCase, BuscarEnderecosUseCase buscarEnderecosUseCase, AtualizarEnderecoUseCase atualizarEnderecoUseCase, BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase) {
        this.criarEnderecoUseCase = criarEnderecoUseCase;
        this.buscarEnderecosUseCase = buscarEnderecosUseCase;
        this.atualizarEnderecoUseCase = atualizarEnderecoUseCase;
        this.buscarEnderecoPorIdUseCase = buscarEnderecoPorIdUseCase;
    }

    @Operation(summary = "Cria um novo endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EnderecoResponseDto> criar(
            @Valid @RequestBody EnderecoRequestDto enderecoRequestDto
    ) {
        EnderecoResponseDto enderecoResponseDto = criarEnderecoUseCase.execute(enderecoRequestDto);
        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca todos os endereços com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping
    public ResponseEntity<RespostaPaginada<EnderecoResponseDto>> buscar(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<EnderecoResponseDto> respostaPaginada = buscarEnderecosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um endereço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EnderecoRequestDto enderecoRequestDto
    ) {
        EnderecoResponseDto enderecoResponseDto = atualizarEnderecoUseCase.execute(id, enderecoRequestDto);
        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca um endereço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<EnderecoResponseDto> buscarPorId(
            @PathVariable Long id
    ) {
        EnderecoResponseDto enderecoResponseDto = buscarEnderecoPorIdUseCase.execute(id);
        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.OK);
    }
}