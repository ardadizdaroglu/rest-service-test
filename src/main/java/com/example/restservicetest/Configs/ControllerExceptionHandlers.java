package com.example.restservicetest.Configs;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ArrayIndexOutOfBoundsException.class })
    protected ResponseEntity<Object> arrayIndexOutOfBoundsException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "You entered a larger number than the existing number of floors";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
