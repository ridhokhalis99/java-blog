package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No post with slug: " + slug
                        )
                );
    }

    public Post createPost(Post post) {
        try {
            post.setCreatedAt(Instant.now().getEpochSecond());
            return postRepository.save(post);
        } catch (DataIntegrityViolationException e) {
            Throwable root = e.getRootCause();
            String message = "Constraint violation";
            if (root != null && root.getMessage().contains("slug")) {
                message = "Slug must be unique";
            }
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    message,
                    e
            );
        }
    }

    public Post updatePostBySlug(String slug, Post newPost) {
        Post savedPost = postRepository.findBySlug(slug).orElse(null);
        if(savedPost == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No post with slug: " + slug
            );
        }
        newPost.setId(savedPost.getId());
        return postRepository.save(newPost);
    }

    public void deletePostBySlug(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No post with slug: " + slug
                ));

        try {
            postRepository.delete(post);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete post due to database constraints",
                    e
            );
        }

    }
}
