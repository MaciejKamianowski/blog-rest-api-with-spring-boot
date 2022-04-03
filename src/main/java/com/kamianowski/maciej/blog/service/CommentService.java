package com.kamianowski.maciej.blog.service;

import com.kamianowski.maciej.blog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto dto);

}
