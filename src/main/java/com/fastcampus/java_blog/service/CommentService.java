package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.request.CreateCommentRequest;
import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.mapper.CommentMapper;
import com.fastcampus.java_blog.repository.CommentRepository;
import com.fastcampus.java_blog.repository.PostRepository;
import com.fastcampus.java_blog.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentMapper commentMapper;

    public Iterable<Comment> getAllCommentsBySlug(String slug) {
        return commentRepository.findCommentsByPostSlug(slug);
    }

    public CommentResponse createComment(CreateCommentRequest request) {
        try {
            Comment comment = commentMapper.toEntity(request, postRepository);
            commentRepository.save(comment);
            return commentMapper.toResponse(comment);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    e
            );
        }

    }
}
