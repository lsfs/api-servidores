package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.servidortemporario.*;
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
@RequestMapping("/servidor-temporario")
@Tag(name = "ServidorTemporário", description = "Operações relacionadas a servidores temporários")
public class ServidorTemporarioController {

    private final CriarServidorTempUseCase criarServidorTempUseCase;
    private final BuscarServidorTempPorIdUseCase buscarServidorTempPorIdUseCase;
    private final BuscarServidoresTemporariosUseCase buscarServidoresTemporariosUseCase;
    private final AtualizarServidorTemporarioUseCase atualizarServidorTemporarioUseCase;
    private final DeletarServidorTemporarioUseCase deletarServidorTemporarioUseCase;

    public ServidorTemporarioController(CriarServidorTempUseCase criarServidorTempUseCase, BuscarServidorTempPorIdUseCase buscarServidorTempPorIdUseCase, BuscarServidoresTemporariosUseCase buscarServidoresTemporariosUseCase, AtualizarServidorTemporarioUseCase atualizarServidorTemporarioUseCase, DeletarServidorTemporarioUseCase deletarServidorTemporarioUseCase) {
        this.criarServidorTempUseCase = criarServidorTempUseCase;
        this.buscarServidorTempPorIdUseCase = buscarServidorTempPorIdUseCase;
        this.buscarServidoresTemporariosUseCase = buscarServidoresTemporariosUseCase;
        this.atualizarServidorTemporarioUseCase = atualizarServidorTemporarioUseCase;
        this.deletarServidorTemporarioUseCase = deletarServidorTemporarioUseCase;
    }

    @Operation(summary = "Cria um novo servidor temporário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servidor temporário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorTempResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ServidorTempResponseDto> criar(
            @RequestBody ServidorTempRequestDto servidorTempRequestDto
    ) {
        ServidorTempResponseDto servidorTempResponseDto = criarServidorTempUseCase.execute(servidorTempRequestDto);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca um servidor temporário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor temporário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorTempResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServidorTempResponseDto> buscarPorId(@PathVariable Long id) {
        ServidorTempResponseDto servidorTempResponseDto = buscarServidorTempPorIdUseCase.execute(id);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Busca todos os servidores temporários com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidores temporários encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaPaginada.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<RespostaPaginada<ServidorTempResponseDto>> buscarServidoresTemporarios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorTempResponseDto> resposta = buscarServidoresTemporariosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um servidor temporário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor temporário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServidorTempResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServidorTempResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody ServidorTempRequestDto servidorTempRequestDto
    ) {
        ServidorTempResponseDto servidorTempResponseDto = atualizarServidorTemporarioUseCase.execute(id, servidorTempRequestDto);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Deleta um servidor temporário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor temporário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletar(@PathVariable Long id) {
        var response = deletarServidorTemporarioUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}