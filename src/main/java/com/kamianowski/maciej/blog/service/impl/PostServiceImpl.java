package com.kamianowski.maciej.blog.service.impl;

import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.repository.PostRepository;
import com.kamianowski.maciej.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto dto) {

        // convert Dto to entity
        Post newPost = postRepository.save(dto.convertDtoToEntity());

        // convert Entity to dto
        PostDto postResponse = newPost.convertEntityToDto();
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> post.convertEntityToDto())
                .collect(Collectors.toList());
    }


}
