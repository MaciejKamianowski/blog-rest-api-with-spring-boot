package com.kamianowski.maciej.blog.service.impl;
import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.payload.PostDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceImplTest {

    @Test
    public void should_convert_entity_to_dto() {
//  Given
        Post post = new Post();
        post.setId(123L);
        post.setTitle("title");
        post.setContent("content");
        post.setDescription("description");
//  When
        PostDto dto = post.convertEntityToDto();
//  Then
        assertThat(post)
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }

}
