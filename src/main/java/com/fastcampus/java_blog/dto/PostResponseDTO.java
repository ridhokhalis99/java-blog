package com.fastcampus.java_blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private Integer id;
    private String title;
    private String slug;
    private String body;
}
