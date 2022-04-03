package com.kamianowski.maciej.blog.service;

import com.kamianowski.maciej.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto dto);
    List<CommentDto> getCommentsByPostId(Long idPost);
    CommentDto getCommentById(Long postId, Long commentId);
}
