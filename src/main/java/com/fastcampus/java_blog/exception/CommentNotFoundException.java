package com.fastcampus.java_blog.exception;

public class CommentNotFoundException extends ApiException {

    public CommentNotFoundException(Integer id) {
        super("Comment not found for id: " + id, 404);
    }
}
