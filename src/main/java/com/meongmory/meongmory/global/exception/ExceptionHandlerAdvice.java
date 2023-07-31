package com.meongmory.meongmory.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseRes> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getBaseResponseCode().getStatus())
                .body(new BaseRes(e.getBaseResponseCode().getStatus().value(), e.getBaseResponseCode().getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseRes> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Objects.requireNonNull(e.getFieldError());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseRes(HttpStatus.BAD_REQUEST.value(), String.format("%s", e.getFieldError().getDefaultMessage())));
    }
}