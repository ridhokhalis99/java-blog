    package com.fastcampus.java_blog.mapper;

    import com.fastcampus.java_blog.repository.PostRepository;
    import com.fastcampus.java_blog.response.CommentResponse;
    import com.fastcampus.java_blog.request.CreateCommentRequest;
    import com.fastcampus.java_blog.entity.Comment;
    import org.mapstruct.Context;
    import org.mapstruct.InheritConfiguration;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring", uses = PostMapper.class)
    public interface CommentMapper {
        @Mapping(target = "post", expression = "java(postRepository.findBySlug(dto.getPostSlug()))")
        Comment toEntity(CreateCommentRequest dto, @Context PostRepository postRepository);

        @InheritConfiguration
        CommentResponse toResponse(Comment comment);
    }
