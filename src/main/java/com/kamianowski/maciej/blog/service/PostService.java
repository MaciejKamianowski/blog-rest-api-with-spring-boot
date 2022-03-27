package com.kamianowski.maciej.blog.service;

import com.kamianowski.maciej.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto dto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
}
