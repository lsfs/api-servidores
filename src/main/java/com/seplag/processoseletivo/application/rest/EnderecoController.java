package com.seplag.processoseletivo.application.rest;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.endereco.AtualizarEnderecoUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.BuscarEnderecosUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.CriarEnderecoUseCase;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final CriarEnderecoUseCase criarEnderecoUseCase;
    private final BuscarEnderecosUseCase buscarEnderecosUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;

    public EnderecoController(CriarEnderecoUseCase criarEnderecoUseCase, BuscarEnderecosUseCase buscarEnderecosUseCase, AtualizarEnderecoUseCase atualizarEnderecoUseCase) {
        this.criarEnderecoUseCase = criarEnderecoUseCase;
        this.buscarEnderecosUseCase = buscarEnderecosUseCase;
        this.atualizarEnderecoUseCase = atualizarEnderecoUseCase;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> criar(
            @RequestBody EnderecoRequestDto enderecoRequestDto
    ) {
        EnderecoResponseDto enderecoResponseDto = criarEnderecoUseCase.execute(enderecoRequestDto);
        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RespostaPaginada<EnderecoResponseDto>> buscar(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") int tamanho
    ) {
        RespostaPaginada<EnderecoResponseDto> respostaPaginada = buscarEnderecosUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody EnderecoRequestDto enderecoRequestDto
    ) {
        EnderecoResponseDto enderecoResponseDto = atualizarEnderecoUseCase.execute(id, enderecoRequestDto);
        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.OK);
    }
}
