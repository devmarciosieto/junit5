package br.com.mmmsieto.financial.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static br.com.mmmsieto.financial.domain.entity.Account.AccountBuilder;

public class ContaTest {

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

}
