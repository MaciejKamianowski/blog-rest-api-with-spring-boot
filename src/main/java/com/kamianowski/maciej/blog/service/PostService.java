package com.kamianowski.maciej.blog.service;

import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto dto);
    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto dto, Long id);
    void deletePostById(Long id);
}
