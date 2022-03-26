package com.kamianowski.maciej.blog.payload;

import com.kamianowski.maciej.blog.entity.Post;
import lombok.Data;

import java.lang.reflect.Field;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    public Post convertDtoToEntity() {
        Field[] dtoFields = this.getClass().getDeclaredFields();
        Object[] values = new Object[dtoFields.length];
        Post entity = new Post();
//        assign values to entity
        for (int i = 0; i < dtoFields.length; i++) {
            try {
                values[i] = dtoFields[i].get(this);
                Field field = entity.getClass().getDeclaredField(dtoFields[i].getName());
                field.setAccessible(true);
                field.set(entity, values[i]);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
}
