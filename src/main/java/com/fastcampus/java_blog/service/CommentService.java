package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.dto.CreateCommentDTO;
import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.mapper.CommentMapper;
import com.fastcampus.java_blog.repository.CommentRepository;
import com.fastcampus.java_blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    PostService postService;

    public Iterable<Comment> getAllCommentsBySlug(String slug) {
        return commentRepository.findCommentsByPostSlug(slug);
    }

    public Comment createComment(CreateCommentDTO dto) {
        Comment comment = commentMapper.toEntity(dto, postService);
        comment.setCreatedAt(Instant.now().getEpochSecond());
        return commentRepository.save(comment);
    }
}
