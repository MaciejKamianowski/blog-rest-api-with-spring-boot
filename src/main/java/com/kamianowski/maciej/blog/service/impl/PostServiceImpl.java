package com.kamianowski.maciej.blog.service.impl;

import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.exception.ResourceNotFoundException;
import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.repository.PostRepository;
import com.kamianowski.maciej.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        return post.convertEntityToDto();
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        // get post by id from the database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        post.assignEntityFieldsByDto(dto);

        Post updatedPost = postRepository.save(post);
        return updatedPost.convertEntityToDto();
    }


}
