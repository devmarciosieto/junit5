package br.com.mmmsieto.service;

import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;
import br.com.mmmsieto.financial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    @DisplayName("Should return a user by email by calling the method more than once")
    void should_return_a_user_by_email_by_calling_the_method_more_than_once() {

        final String EMAIL = "email@hotmail.com";

        UserRepository repository = mock(UserRepository.class);
        userService = new UserService(repository);

//        when(repository.getUserByEmail(EMAIL))
//                .thenReturn(Optional.of(UserBuilderTest.builder().email("email01@gmail.com").build()))
//                .thenReturn(Optional.of(UserBuilderTest.builder().email("email02@gmail.com").build()))
//                .thenReturn(Optional.of(UserBuilderTest.builder().email("email03@gmail.com").build()))
//                .thenReturn(Optional.of(UserBuilderTest.builder().email("email04@gmail.com").build()));

        when(repository.getUserByEmail(EMAIL))
                .thenReturn(Optional.of(UserBuilderTest.builder().email("email01@gmail.com").build()),
                        Optional.of(UserBuilderTest.builder().email("email02@gmail.com").build()),
                        Optional.of(UserBuilderTest.builder().email("email03@gmail.com").build()),
                        Optional.of(UserBuilderTest.builder().email("email04@gmail.com").build()));


        Optional<User> user = userService.getUserByEmail(EMAIL);
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);

        user = userService.getUserByEmail("other@gmail.com");
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);



        verify(repository, atLeastOnce()).getUserByEmail(EMAIL);
        verify(repository, never()).getUserByEmail("other1@gmail.com");
    }

}
