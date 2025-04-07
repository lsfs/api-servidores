package com.seplag.processoseletivo.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seplag.processoseletivo.application.dto.shared.MensagemErro;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        MensagemErro mensagemErro = new MensagemErro(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Acesso não autorizado",
                "Você precisa estar autenticado para acessar este recurso.",
                request.getRequestURI()
        );


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(mensagemErro));
    }
}