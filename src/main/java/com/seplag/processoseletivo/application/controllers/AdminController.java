package com.seplag.processoseletivo.application.controllers;


import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin")
@RequestMapping("/admin")
@RestController
public class AdminController {


    @Operation(summary = "Verifica a permissao do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso permitido",
                    content = @Content(mediaType = "application/text", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Acesso negado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<String> verificaPermissao() {
        return ResponseEntity.ok("Acesso permitido");
    }

}
