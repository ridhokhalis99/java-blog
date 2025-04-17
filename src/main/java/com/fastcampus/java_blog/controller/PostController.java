package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public Post getPost(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
}
