package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.request.CreateCommentRequest;
import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.response.CommentResponse;
import com.fastcampus.java_blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public Iterable<Comment> getComments(@RequestParam String post_slug){
        return new ArrayList<>();
    }

    @PostMapping
    public CommentResponse createComment(@Valid @RequestBody CreateCommentRequest request){
        return commentService.createComment(request);
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable Integer id){
        return commentService.getCommentById(id);
    }
}
