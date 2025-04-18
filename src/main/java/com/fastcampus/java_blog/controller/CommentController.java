package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired

    @GetMapping
    public Iterable<Comment> getComments(@RequestParam String post_slug){
        return new ArrayList<>();
    }
}
