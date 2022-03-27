package com.kamianowski.maciej.blog.entity;

import com.kamianowski.maciej.blog.payload.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Column(
            name = "title", nullable = false
    )
    private String title;

    @Column(
            name = "description", nullable = false
    )
    private String description;

    @Column(
            name = "content", nullable = false
    )
    private String content;

    public PostDto convertEntityToDto() {
        Field [] entityFields = this.getClass().getDeclaredFields();
        Object [] values = new Object[entityFields.length];
        PostDto dto = new PostDto();
        // assign values to Dto
        for (int i = 0; i < entityFields.length; i++) {
            try {
                values[i] = entityFields[i].get(this);
                Field field = dto.getClass().getDeclaredField(entityFields[i].getName());
                field.setAccessible(true);
                field.set(dto, values[i]);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    public void assignEntityFieldsByDto(PostDto dto) {
        Field [] entityFields = this.getClass().getDeclaredFields();
        Field [] dtoFields = dto.getClass().getDeclaredFields();

        for (int i = 0; i < entityFields.length; i++) {
            try {
                if (entityFields[i].getName().equals("id")) {
                    continue;
                }
                dtoFields[i].setAccessible(true);
                entityFields[i].set(this, dtoFields[i].get(dto));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
