package com.fastcampus.java_blog.repository;

import com.fastcampus.java_blog.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findCommentsByPostSlug(String slug);
}
