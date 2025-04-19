package com.fastcampus.java_blog.repository;

import com.fastcampus.java_blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findBySlug(String slug);
    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);
    Page<Post> findAllByIsDeletedFalse(Pageable pageable);
    boolean existsBySlug(String slug);
}