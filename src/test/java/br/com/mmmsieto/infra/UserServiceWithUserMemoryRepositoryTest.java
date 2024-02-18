package br.com.mmmsieto.infra;

import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.infra.UserMemoryRepository;
import br.com.mmmsieto.financial.service.UserService;
import org.junit.jupiter.api.*;

import static br.com.mmmsieto.builders.UserBuilderTest.builder;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceWithUserMemoryRepositoryTest {

    private static UserService service = new UserService(new UserMemoryRepository());

    @Test
    @Order(1)
    @DisplayName("Should save a valid user")
    void should_save_a_valid_user() {
        User user = service.save(builder().id(null).build());
        assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Should reject an existing user")
    void should_reject_an_existing_user() {

        User user = builder().id(null).build();

        ValidationException ex = assertThrows(ValidationException.class, () ->
                service.save(user));

        assertEquals(String.format("There is already a user with this emial: %s registered", user.getEmail()), ex.getMessage());

    }

}
