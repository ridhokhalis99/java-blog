package com.fastcampus.java_blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiExceptionResponse> handleResponseStatusException(ResponseStatusException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getStatusCode().value(),
                ex.getReason()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleOtherExceptions(Exception ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(500, "Unexpected error: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
