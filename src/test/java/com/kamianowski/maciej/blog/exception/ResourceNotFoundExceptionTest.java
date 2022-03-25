package com.kamianowski.maciej.blog.exception;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;  // main one

public class ResourceNotFoundExceptionTest {

    @Test
    public void should_return_proper_exception_description() {
//        Given
        String resourceName = "Post";
        String fieldName = "id";
        String fieldValue = "-1";
//        When
        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);
//        Then
        assertThat(exception.getMessage()).isEqualTo("Post not found with id : -1");
    }
}
