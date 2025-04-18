package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.dto.CreateCommentDTO;
import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.service.CommentService;
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
    public Comment createComment(@RequestBody CreateCommentDTO dto){
        return commentService.createComment(dto);
    }
}
