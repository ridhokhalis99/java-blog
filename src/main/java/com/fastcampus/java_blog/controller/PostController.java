package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getPosts();
    }
}
