package com.fastcampus.java_blog.mapper;

import com.fastcampus.java_blog.entity.Comment;
import com.fastcampus.java_blog.request.CreatePostRequest;
import com.fastcampus.java_blog.response.CommentResponse;
import com.fastcampus.java_blog.response.PostResponse;
import com.fastcampus.java_blog.entity.Post;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(CreatePostRequest createPostRequest);

    @InheritConfiguration
    PostResponse toResponse(Post post);

    @Mapping(target = "post", ignore = true)
    CommentResponse toCommentResponse(Comment comment);
}
