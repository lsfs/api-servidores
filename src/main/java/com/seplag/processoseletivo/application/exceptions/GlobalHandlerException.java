package com.seplag.processoseletivo.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UnidadeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnidadeNotFoundException(UnidadeNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }
}
