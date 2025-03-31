package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.servidortemporario.*;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidor-temporario")
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

    @PostMapping
    public ResponseEntity<ServidorTempResponseDto> criar(
            @RequestBody ServidorTempRequestDto servidorTempRequestDto
    ) {
        ServidorTempResponseDto servidorTempResponseDto = criarServidorTempUseCase.execute(servidorTempRequestDto);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTempResponseDto> buscarPorId(@PathVariable Long id) {
        ServidorTempResponseDto servidorTempResponseDto = buscarServidorTempPorIdUseCase.execute(id);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<RespostaPaginada<ServidorTempResponseDto>> buscarServidoresTemporarios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<ServidorTempResponseDto> resposta = buscarServidoresTemporariosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTempResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody ServidorTempRequestDto servidorTempRequestDto
    ) {
        ServidorTempResponseDto servidorTempResponseDto = atualizarServidorTemporarioUseCase.execute(id, servidorTempRequestDto);
        return new ResponseEntity<>(servidorTempResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletar(@PathVariable Long id) {
        var response = deletarServidorTemporarioUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
