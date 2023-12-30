package br.com.mmmsieto.financial.domain.entity;


import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final Long id = 1L;
    private final String name = "name";
    private final String email = "email@gmail.com";
    private final String password = "123456";
    @Test
    @DisplayName("Should create a valid user")
    void should_create_a_valid_user() {
        User user = UserBuilderTest.builder().build();
        assertAll("user",
                () -> assertEquals(id, user.getId()),
                () -> assertEquals(name, user.getName()),
                () -> assertEquals(email, user.getEmail()),
                () -> assertEquals(password, user.getPassword())
        );

    }

    @Test
    @DisplayName("Should throw a ValidationException when name is null ou empty")
    void should_throw_a_ValidationException_when_name_is_nul_or_empty() {
        var exception = assertThrows(ValidationException.class, () ->  UserBuilderTest.builder().name(null).build());
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a ValidationException when email is null ou empty")
    void should_throw_a_ValidationException_when_email_is_null_or_empty() {
        var exception = assertThrows(ValidationException.class, () -> UserBuilderTest.builder().email("").build());
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a ValidationException when password is null ou empty")
    void should_throw_a_ValidationException_when_name_is_null() {
        var exception = assertThrows(ValidationException.class, () -> UserBuilderTest.builder().password(null).build());
        assertEquals("Password is required", exception.getMessage());
    }

}