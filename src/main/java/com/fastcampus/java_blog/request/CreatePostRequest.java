package com.fastcampus.java_blog.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreatePostRequest {
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Slug cannot be empty")
    private String slug;

    @NotEmpty(message = "Body cannot be empty")
    private String body;
}
