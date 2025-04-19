package com.fastcampus.java_blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiExceptionResponse {
    private int status;
    private String message;
}
