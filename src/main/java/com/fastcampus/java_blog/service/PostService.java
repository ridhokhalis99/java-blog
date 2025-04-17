package com.fastcampus.java_blog.service;

import com.fastcampus.java_blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {
    Post post1 = new Post(1, "title1", "slug1");
    Post post2 = new Post(2, "title2", "slug2");
    @Getter
    List<Post> posts = new ArrayList<Post>(Arrays.asList(post1, post2));

}
