package com.fastcampus.java_blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String name;
    private String email;
    private String body;
    private PostResponse post;
}
