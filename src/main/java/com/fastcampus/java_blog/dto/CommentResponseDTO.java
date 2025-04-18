package com.fastcampus.java_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String body;
}
