package com.example.demo.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex){
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("Processing failed",ex.getMessage()));
    }
}
