package com.kamianowski.maciej.blog.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordEncoderGeneratorTests {
    @Test
    void shouldEncodePasswordToArrayWithEqualLength() {
        // given
        PasswordEncoderGenerator generator = new PasswordEncoderGenerator();
        String weakPassword = "admin123";
        String strongPassword = "932@dmini$tr&tor_Xu%";

        // when
        String encodedWeakPassword = generator.encodePassword(weakPassword);
        String encodedStrongPassword = generator.encodePassword(strongPassword);

        // then
        assertEquals(encodedStrongPassword.length(), encodedWeakPassword.length());
    }
}
