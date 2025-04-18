package com.fastcampus.java_blog.mapper;

import com.fastcampus.java_blog.dto.CreatePostDTO;
import com.fastcampus.java_blog.dto.PostResponseDTO;
import com.fastcampus.java_blog.entity.Post;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(CreatePostDTO createPostDTO);

    @InheritConfiguration
    PostResponseDTO toDto(Post post);
}
