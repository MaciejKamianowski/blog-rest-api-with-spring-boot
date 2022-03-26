package com.kamianowski.maciej.blog.controller;

import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto) {
        return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
    }
}
