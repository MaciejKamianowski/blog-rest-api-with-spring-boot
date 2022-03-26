package com.kamianowski.maciej.blog.service.impl;

import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.repository.PostRepository;
import com.kamianowski.maciej.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto dto) {

        // convert Dto to entity
        Post post = new Post();
        post.setId(dto.getId());
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        Post newPost = postRepository.save(post);

        // convert Entity to dto
//        PostDto postResponse = new PostDto();
        PostDto postResponse = post.convertEntityToDto();
        return postResponse;
    }


}
