package com.fastcampus.java_blog.controller;

import com.fastcampus.java_blog.request.CreatePostRequest;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.response.PostResponse;
import com.fastcampus.java_blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public PostResponse getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @PutMapping("/{slug}")
    public PostResponse updatePostBySlug(@PathVariable String slug, @RequestBody Post post) {
        return postService.updatePostBySlug(slug, post);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Map<String,String>> deletePostBySlug(@PathVariable String slug) {
        postService.deletePostBySlug(slug);
        return ResponseEntity
                .ok(Collections.singletonMap("message", "Post deleted successfully"));
    }

    @PostMapping("/{slug}/publish")
    public ResponseEntity<Map<String, String>> publishPostBySlug(@PathVariable String slug) {
        postService.publishPostBySlug(slug);
        return ResponseEntity
                .ok(Collections.singletonMap("message", "Post published successfully"));
    }
}
