package com.kamianowski.maciej.blog.service.impl;

import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.exception.ResourceNotFoundException;
import com.kamianowski.maciej.blog.payload.PostDto;
import com.kamianowski.maciej.blog.payload.PostResponse;
import com.kamianowski.maciej.blog.repository.PostRepository;
import com.kamianowski.maciej.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;
    final private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto dto) {

        // convert Dto to entity
        Post newPost = postRepository.save(mapToEntity(dto));

        // convert Entity to dto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortBy
                .equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        // get post by id from the database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        Post updatedPost = postRepository.save(mapToEntity(dto));
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post) {
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }

    private Post mapToEntity(PostDto dto) {
        Post post = modelMapper.map(dto, Post.class);
        return post;
    }
}
