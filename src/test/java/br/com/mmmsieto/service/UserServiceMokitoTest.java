package br.com.mmmsieto.service;

import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.repository.UserRepository;
import br.com.mmmsieto.financial.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceMokitoTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    final String EMAIL = "email@hotmail.com";
    final String EMAIL_OTHER = "other@gmail.com";


//    void setup() {
    // no longer needed because of @ExtendWith(MockitoExtension.class)
//    @BeforeEach
//        MockitoAnnotations.openMocks(this);
//    }

//    @AfterEach
//    void tearDown(){
//        verifyNoMoreInteractions(repository);
//    }

    @Test
    @DisplayName("Should save a user successfully")
    void should_save_a_user_successfully() {

        User userToSave = UserBuilderTest.builder().id(null).build();

        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.empty());

        when(repository.save(userToSave)).thenReturn(UserBuilderTest.builder().build());

        User user = userService.save(userToSave);

        assertNotNull(user.getEmail());
        verify(repository).getUserByEmail(userToSave.getEmail());
        verify(repository).save(userToSave);
    }

    @Test
    @DisplayName("Should reject existing user")
    void should_reject_existing_user() {

        User userToSave = UserBuilderTest.builder().id(null).build();

        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.of(UserBuilderTest.builder().build()));

        ValidationException ex = assertThrows(ValidationException.class, () ->
                userService.save(userToSave));

        assertTrue(ex.getMessage().endsWith("registered"));

        verify(repository, never()).save(userToSave);
    }

    @Test
    @DisplayName("should return a non-existent empty user")
    void should_return_a_non_existent_empty_user() {

        // como esse já é o comportamento patrão, não precisa
//        Mockito.when(repository.getUserByEmail("email@hotmail.com"))
//                .thenReturn(Optional.empty());

        Optional<User> user = userService.getUserByEmail(EMAIL);
        assertTrue(user.isEmpty());

    }

    @Test
    @DisplayName("Should return a user by email")
    void should_return_a_user_by_email() {

        when(repository.getUserByEmail(EMAIL))
                .thenReturn(Optional.of(UserBuilderTest.builder().email(EMAIL).build()));

        Optional<User> user = userService.getUserByEmail(EMAIL);
        assertTrue(user.isPresent());
        assertEquals(EMAIL, user.get().getEmail());

        verify(repository).getUserByEmail(EMAIL);
        verify(repository, times(1)).getUserByEmail(EMAIL);
        verify(repository, atLeastOnce()).getUserByEmail(EMAIL);
        verify(repository, never()).getUserByEmail(EMAIL_OTHER);
        verifyNoMoreInteractions(repository);

    }

    @Test
    @DisplayName("Should return a user by email by calling the method more than once")
    void should_return_a_user_by_email_by_calling_the_method_more_than_once() {




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

        user = userService.getUserByEmail(EMAIL_OTHER);
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);

        user = userService.getUserByEmail(EMAIL);
        System.out.println(user);


        verify(repository, atLeastOnce()).getUserByEmail(EMAIL);
        verify(repository, never()).getUserByEmail("other1@gmail.com");
    }

}
