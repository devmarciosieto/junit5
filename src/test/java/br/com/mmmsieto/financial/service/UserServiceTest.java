package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.service.UserService;
import br.com.mmmsieto.infra.UserDummyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.mmmsieto.builders.UserBuilderTest.builder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    private UserService userService;

    @Test
    @DisplayName("Should save a user successfully")
    void should_save_a_user_successfully() {

        userService = new UserService(new UserDummyRepository());
        User user = userService.save(builder().id(null).email("outro@gmail.com").build());
        assertNotNull(user.getId());

    }

}
