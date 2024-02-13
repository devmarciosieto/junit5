package br.com.mmmsieto.financial.domain.entity;

import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.com.mmmsieto.financial.domain.entity.Account.AccountBuilder;
import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    @DisplayName("should create valid account")
    void should_create_valid_account() {

        final var name = "Márcio Rogérios Sieto";
        final var user = new User();

        Account account = AccountBuilder
                .newAccount()
                .id(1L)
                .name(name)
                .user(user)
                .build();

        assertAll("Account",
                () -> assertEquals(1L, account.getId()),
                () -> assertEquals(name, account.getName()),
                () -> assertEquals(user, account.getUser())
        );

    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    @DisplayName("should create invalid account")
    void should_reject_invalid_account(Long id, String name, User user, String message) {

        String errorMessage = assertThrows(ValidationException.class, () ->
                AccountBuilder
                        .newAccount()
                        .id(id)
                        .name(name)
                        .user(user)
                        .build()
        ).getMessage();

        assertEquals(message, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, new User(), "Name is mandatory"),
                Arguments.of( 1L, "Márcio R. Sieto", null, "User is mandatory")
        );
    }

}
