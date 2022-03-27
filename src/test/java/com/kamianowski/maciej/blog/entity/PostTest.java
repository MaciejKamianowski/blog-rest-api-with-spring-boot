package com.kamianowski.maciej.blog.entity;

import com.kamianowski.maciej.blog.entity.Post;
import com.kamianowski.maciej.blog.payload.PostDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    @Test
    public void should_assign_entity_fields_by_dto() {
//  Given
        Post post = new Post();
        post.setId(1L);
        PostDto dto = new PostDto();
        dto.setId(1L);
        dto.setTitle("title");
        dto.setContent("content");
        dto.setDescription("description");
//  When
        post.assignEntityFieldsByDto(dto);
//  Then
        assertThat(post)
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }


}
