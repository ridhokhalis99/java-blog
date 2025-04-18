package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.entity.Post;
import com.fastcampus.java_blog.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Iterable<Comment> getAllCommentsBySlug(String slug) {
        return commentRepository.findCommentsByPostSlug(slug);
    }

}
