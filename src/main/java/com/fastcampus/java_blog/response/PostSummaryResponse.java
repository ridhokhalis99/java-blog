package com.fastcampus.java_blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummaryResponse {
    private String title;
    private String slug;
    private String body;
}
