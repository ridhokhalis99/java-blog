package com.fastcampus.java_blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleOtherExceptions(Exception ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(500, "Unexpected error: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
