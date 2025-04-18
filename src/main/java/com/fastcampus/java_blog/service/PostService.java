package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.dto.CreatePostDTO;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.mapper.PostMapper;
import com.fastcampus.java_blog.repository.PostRepository;
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

    @Autowired
    PostMapper postMapper;

    public Iterable<Post> getPosts() {
        return postRepository.findAllByIsDeletedFalse();
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No post with slug: " + slug
                        )
                );
    }

    public Post createPost(CreatePostDTO postRequest) {
        try {
            Post post = postMapper.toEntity(postRequest);
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
        Post savedPost = postRepository.findBySlugAndIsDeleted(slug, false).orElse(null);
        if(savedPost == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No post with slug: " + slug
            );
        }
        newPost.setCreatedAt(savedPost.getCreatedAt());
        newPost.setId(savedPost.getId());
        return postRepository.save(newPost);
    }

    public void deletePostBySlug(String slug) {
        Post post = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No post with slug: " + slug
                ));

        try {
            post.setDeleted(true);
            postRepository.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete post due to database constraints",
                    e
            );
        }

    }

    public void publishPostBySlug(String slug) {
        Post post = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No post with slug: " + slug
                ));

        if (post.isPublished()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Post already published"
            );
        }

        try {
            post.setPublished(true);
            post.setPublishedAt(Instant.now().getEpochSecond());
            postRepository.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot publish post due to database constraints",
                    e
            );
        }
    }
}
