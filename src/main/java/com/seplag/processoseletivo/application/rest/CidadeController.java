package com.seplag.processoseletivo.application.rest;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.AtualizarCidadeUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.CriarCidadeUseCase;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
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

    @PostMapping
    public ResponseEntity<CidadeResponseDto> criarCidade(
            @RequestBody CidadeRequestDto cidadeRequestDto
    ) {
        var response = this.criarCidadeUseCase.execute(cidadeRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDto> buscarCidadePorId(
            @PathVariable Long id
    ) {
        var response = this.buscarCidadesPorIdUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespostaPaginada<CidadeResponseDto>> buscarCidades(
            @RequestParam(value = "page", defaultValue = "0") int pagina,
            @RequestParam(value = "size", defaultValue = "10") int tamanho
    ) {
        var respostaPaginada = this.buscarCidadesUseCase.execute(pagina, tamanho);
        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDto> atualizarCidade(
            @PathVariable Long id,
            @RequestBody CidadeRequestDto cidadeRequestDto
    ) {
        var response = this.atualizarCidadeUseCase.execute(id, cidadeRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
