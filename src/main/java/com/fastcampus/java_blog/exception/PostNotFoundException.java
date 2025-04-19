package com.fastcampus.java_blog.exception;

public class PostNotFoundException extends ApiException {

    public PostNotFoundException(String slug) {
        super("Post not found for slug: " + slug, 404);
    }
}
