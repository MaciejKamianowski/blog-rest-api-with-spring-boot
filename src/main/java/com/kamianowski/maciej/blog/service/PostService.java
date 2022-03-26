package com.kamianowski.maciej.blog.service;

import com.kamianowski.maciej.blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto dto);
}
