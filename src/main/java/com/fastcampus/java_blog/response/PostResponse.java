package com.fastcampus.java_blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String title;
    private String slug;
    private String body;
    private List<CommentResponse> comments;
}
