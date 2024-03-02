package br.com.mmmsieto.service;

import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.builders.UserBuilder;
import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;
import br.com.mmmsieto.financial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceMokitoTest {

    private UserService userService;

    @Test
    @DisplayName("Should save a user successfully")
    void should_save_a_user_successfully() {

        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);


    }

    @Test
    @DisplayName("should return a non-existent empty user")
    void should_return_a_non_existent_empty_user() {

        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);

        // como esse já é o comportamento patrão, não precisa
//        Mockito.when(repository.getUserByEmail("email@hotmail.com"))
//                .thenReturn(Optional.empty());

        Optional<User> user = userService.getUserByEmail("email@hotmail.com");
        assertTrue(user.isEmpty());

    }

    @Test
    @DisplayName("Should return a user by email")
    void should_return_a_user_by_email() {

        final String EMAIL = "email@hotmail.com";

        UserRepository repository = mock(UserRepository.class);
        userService = new UserService(repository);

        when(repository.getUserByEmail(EMAIL))
                .thenReturn(Optional.of(UserBuilderTest.builder().email(EMAIL).build()));

        Optional<User> user = userService.getUserByEmail(EMAIL);
        assertTrue(user.isPresent());
        assertEquals(EMAIL, user.get().getEmail());

        verify(repository).getUserByEmail(EMAIL);
        verify(repository, times(1)).getUserByEmail(EMAIL);
        verify(repository, atLeastOnce()).getUserByEmail(EMAIL);
        verify(repository, never()).getUserByEmail("other@gmail.com");
        verifyNoMoreInteractions(repository);
    }

}
