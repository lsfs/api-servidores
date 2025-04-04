package com.seplag.processoseletivo.application.controllers;

import com.seplag.processoseletivo.application.dto.autenticacao.LoginRequestDto;
import com.seplag.processoseletivo.application.dto.autenticacao.LoginResponseDto;
import com.seplag.processoseletivo.application.usecases.autenticacao.AutenticaUsuarioUseCase;
import com.seplag.processoseletivo.application.usecases.autenticacao.RefreshTokenUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticaUsuarioUseCase autenticaUsuarioUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AuthController(AutenticaUsuarioUseCase autenticaUsuarioUseCase, RefreshTokenUseCase refreshTokenUseCase) {
        this.autenticaUsuarioUseCase = autenticaUsuarioUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = autenticaUsuarioUseCase.execute(loginRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestBody String refreshToken) {
        LoginResponseDto response = refreshTokenUseCase.execute(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
