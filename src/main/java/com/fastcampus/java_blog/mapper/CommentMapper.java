    package com.fastcampus.java_blog.mapper;

    import com.fastcampus.java_blog.dto.CommentResponseDTO;
    import com.fastcampus.java_blog.dto.CreateCommentDTO;
    import com.fastcampus.java_blog.entity.Comment;
    import com.fastcampus.java_blog.entity.Post;
    import com.fastcampus.java_blog.service.PostService;
    import org.mapstruct.Context;
    import org.mapstruct.InheritConfiguration;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring")
    public interface CommentMapper {
        @Mapping(target = "post", expression = "java(postService.getPostBySlug(dto.getPostSlug()))")
        Comment toEntity(CreateCommentDTO dto, @Context PostService postService);

        @InheritConfiguration
        CommentResponseDTO toDtoResponse(Comment comment);
    }
