package com.kamianowski.maciej.blog.controller;

import com.kamianowski.maciej.blog.payload.CommentDto;
import com.kamianowski.maciej.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") Long postId,
            @RequestBody CommentDto dto) {
        return new ResponseEntity<>(commentService.createComment(postId, dto), HttpStatus.CREATED);
    }


}
