package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginRequestDto;
import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;
import com.seplag.processoseletivo.application.dto.autenticacao.RefreshTokenRequestDto;
import com.seplag.processoseletivo.application.dto.autenticacao.UsuarioRequestDto;
import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.autenticacao.AutenticaUsuarioUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.CadastraUsuarioUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.RefreshTokenUseCase;
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
@RequestMapping("/auth")
@Tag(name = "Autenticação")
@Validated
public class AuthController {

    private final AutenticaUsuarioUseCase autenticaUsuarioUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final CadastraUsuarioUseCase cadastraUsuarioUseCase;

    public AuthController(AutenticaUsuarioUseCase autenticaUsuarioUseCase, RefreshTokenUseCase refreshTokenUseCase, CadastraUsuarioUseCase cadastraUsuarioUseCase) {
        this.autenticaUsuarioUseCase = autenticaUsuarioUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.cadastraUsuarioUseCase = cadastraUsuarioUseCase;
    }

    @Operation(summary = "Realiza login do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = autenticaUsuarioUseCase.execute(loginRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza o token de autenticação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshToken) {
        LoginResponseDto response = refreshTokenUseCase.execute(refreshToken.token());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensagemRetorno.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    @PostMapping("/cadastro")
    public ResponseEntity<MensagemRetorno> cadastraUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        MensagemRetorno response = cadastraUsuarioUseCase.execute(usuarioRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}