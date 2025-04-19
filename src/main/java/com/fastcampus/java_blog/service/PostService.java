package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.exception.PostNotFoundException;
import com.fastcampus.java_blog.request.CreatePostRequest;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.mapper.PostMapper;
import com.fastcampus.java_blog.repository.PostRepository;
import com.fastcampus.java_blog.response.PaginatedResponse;
import com.fastcampus.java_blog.response.PostResponse;
import com.fastcampus.java_blog.response.PostSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

    public PaginatedResponse<PostSummaryResponse> getPosts(Pageable pageable) {
        Page<Post> page = postRepository.findAllByIsDeletedFalse(pageable);

        List<PostSummaryResponse> content = page.getContent()
                .stream()
                .map(postMapper::toSummaryResponse)
                .toList();

        return new PaginatedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public PostResponse getPostBySlug(String slug) {
        Post post = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new PostNotFoundException(slug));
        return postMapper.toResponse(post);
    }

    public PostResponse createPost(CreatePostRequest request) {
        if (postRepository.existsBySlug(request.getSlug()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slug must be unique");

        try {
            Post post = postMapper.toEntity(request);
            postRepository.save(post);
            return postMapper.toResponse(post);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e
            );
        }
    }

    public PostResponse updatePostBySlug(String slug, Post newPost) {
        Post savedPost = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new PostNotFoundException(slug));
        newPost.setId(savedPost.getId());
        Post post = postRepository.save(newPost);
        return postMapper.toResponse(post);
    }

    public void deletePostBySlug(String slug) {
        Post post = postRepository.findBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new PostNotFoundException(slug));
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
                .orElseThrow(() -> new PostNotFoundException(slug));
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
