package com.fastcampus.java_blog.repository;

import com.fastcampus.java_blog.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);
    Iterable<Post> findAllByIsDeletedFalse();
}