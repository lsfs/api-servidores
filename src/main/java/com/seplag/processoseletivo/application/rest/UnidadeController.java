package com.seplag.processoseletivo.application.rest;


import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.*;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final CriaUnidadeUseCase criaUnidadeUseCase;
    private final BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase;
    private final AtualizarUnidadeUseCase atualizarUnidadeUseCase;
    private final BuscarUnidadesUseCase buscaUnidades;
    private final DeletarUnidadeUseCase deletarUnidadeUseCase;

    public UnidadeController(CriaUnidadeUseCase criaUnidadeUseCase, BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase, AtualizarUnidadeUseCase atualizarUnidadeUseCase, BuscarUnidadesUseCase buscaUnidades, DeletarUnidadeUseCase deletarUnidadeUseCase) {
        this.criaUnidadeUseCase = criaUnidadeUseCase;
        this.buscarUnidadePorIdUseCase = buscarUnidadePorIdUseCase;
        this.atualizarUnidadeUseCase = atualizarUnidadeUseCase;
        this.buscaUnidades = buscaUnidades;
        this.deletarUnidadeUseCase = deletarUnidadeUseCase;
    }

    @PostMapping
    public ResponseEntity<UnidadeResponseDto> criarUnidade(@RequestBody UnidadeRequestDto unidadeRequestDto) {
        return new ResponseEntity<>(criaUnidadeUseCase.execute(unidadeRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> buscarUnidadePorId(@PathVariable Long id) {
        return new ResponseEntity<>(buscarUnidadePorIdUseCase.execute(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseDto> atualizarUnidade(@PathVariable Long id, @RequestBody UnidadeRequestDto unidadeRequestDto) {
        return new ResponseEntity<>(atualizarUnidadeUseCase.execute(id, unidadeRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespostaPaginada<UnidadeResponseDto>> buscarUnidades(
            @RequestParam(value = "page", defaultValue = "0") int pagina,
            @RequestParam(value = "size", defaultValue = "10") int tamanho) {

        var respostaPaginada = buscaUnidades.execute(pagina, tamanho);

        return new ResponseEntity<>(respostaPaginada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletarUnidade(@PathVariable Long id) {

        var response = deletarUnidadeUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
