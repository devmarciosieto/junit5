package br.com.mmmsieto.service;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;
import br.com.mmmsieto.financial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserServiceMokitoTest {

    private UserService userService;

    @Test
    @DisplayName("Should save a user successfully")
    void should_save_a_user_successfully() {

        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);


    }

    @Test
    @DisplayName("Should return a user by email")
    void should_return_a_user_by_email() {

        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);

        Optional<User> user = userService.getUserByEmail("email@hotmail.com");
        assertTrue(user.isEmpty());

    }

}
