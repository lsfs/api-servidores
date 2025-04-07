package com.seplag.processoseletivo.application.controllers;


import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoUnidadeEnderecoResponseDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.*;
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
@RequestMapping("/unidades")
@Tag(name = "Unidade", description = "Operações relacionadas a unidades")
@Validated
public class UnidadeController {

    private final CriaUnidadeUseCase criaUnidadeUseCase;
    private final BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase;
    private final AtualizarUnidadeUseCase atualizarUnidadeUseCase;
    private final BuscarUnidadesUseCase buscaUnidades;
    private final DeletarUnidadeUseCase deletarUnidadeUseCase;
    private final BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase buscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase;

    public UnidadeController(CriaUnidadeUseCase criaUnidadeUseCase, BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase, AtualizarUnidadeUseCase atualizarUnidadeUseCase, BuscarUnidadesUseCase buscaUnidades, DeletarUnidadeUseCase deletarUnidadeUseCase, BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase buscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase) {
        this.criaUnidadeUseCase = criaUnidadeUseCase;
        this.buscarUnidadePorIdUseCase = buscarUnidadePorIdUseCase;
        this.atualizarUnidadeUseCase = atualizarUnidadeUseCase;
        this.buscaUnidades = buscaUnidades;
        this.deletarUnidadeUseCase = deletarUnidadeUseCase;
        this.buscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase = buscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase;
    }

    @Operation(summary = "Cria uma nova unidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Argumento inválido", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UnidadeResponseDto> criarUnidade(@Valid @RequestBody UnidadeRequestDto unidadeRequestDto) {
        return new ResponseEntity<>(criaUnidadeUseCase.execute(unidadeRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Busca uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> buscarUnidadePorId(@PathVariable Long id) {
        return new ResponseEntity<>(buscarUnidadePorIdUseCase.execute(id), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadeResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Argumento inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> atualizarUnidade(@PathVariable Long id, @Valid @RequestBody UnidadeRequestDto unidadeRequestDto) {
        return new ResponseEntity<>(atualizarUnidadeUseCase.execute(id, unidadeRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Busca todas as unidades com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidades encontradas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Argumento inválido", content = @Content)
    })
    @GetMapping
    public ResponseEntity<RespostaPaginada<UnidadeResponseDto>> buscarUnidades(
            @RequestParam(value = "page", defaultValue = "0") int pagina,
            @RequestParam(value = "size", defaultValue = "10") int tamanho) {

        var respostaPaginada = buscaUnidades.execute(pagina, tamanho);

        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @Operation(summary = "Deleta uma unidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Unidade deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletarUnidade(@PathVariable Long id) {

        var response = deletarUnidadeUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Busca endereços de unidades por nome do servidor efetivo com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Argumento inválido", content = @Content)
    })
    @GetMapping("/endereco")
    public ResponseEntity<RespostaPaginada<ServidorEfetivoUnidadeEnderecoResponseDto>> buscarEnderecoDeUnidadePorNomeServidorEfetivo(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        RespostaPaginada<ServidorEfetivoUnidadeEnderecoResponseDto> resposta = buscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase.execute(nome, pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
