package com.seplag.processoseletivo.shared.exceptions;

import com.seplag.processoseletivo.application.dto.shared.MensagemErro;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemErro> handleEntityNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                new MensagemErro(
                        HttpStatus.NOT_FOUND.value(),
                        "Entidade não encontrada",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErro> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(
                new MensagemErro(
                        HttpStatus.BAD_REQUEST.value(),
                        "Erro de validação",
                        message,
                        request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<MensagemErro> handleAuthenticationException(
            AuthenticationException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                new MensagemErro(
                        HttpStatus.UNAUTHORIZED.value(),
                        "Não autorizado",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<MensagemErro> handleMissingServletRequestPartException(
            MissingServletRequestPartException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                new MensagemErro(
                        HttpStatus.BAD_REQUEST.value(),
                        "Parte da requisição ausente",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MensagemErro> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        String message = ex.getMessage();
        if (message != null && message.contains("problem:")) {
            message = message.substring(message.indexOf("problem:") + "problem:".length()).trim();
        }

        return new ResponseEntity<>(
                new MensagemErro(
                        HttpStatus.BAD_REQUEST.value(),
                        "Erro de leitura do corpo da requisição",
                        message,
                        request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
