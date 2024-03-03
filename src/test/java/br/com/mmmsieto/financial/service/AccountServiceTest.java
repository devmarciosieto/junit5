package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.builders.UserBuilderTest;
import br.com.mmmsieto.financial.domain.entity.Account;
import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.repository.AccountRepository;
import br.com.mmmsieto.financial.service.external.AccountEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountEvent event;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Test
    @DisplayName("Should save account with success")
    void should_save_account_with_success() throws Exception {

        User user = UserBuilderTest.builder().build();

        Account accountToSave = Account.AccountBuilder
                .newAccount()
                .id(null)
                .name("Test Name")
                .user(user)
                .build();

        Account accountReturn = Account.AccountBuilder
                .newAccount()
                .id(1L)
                .name("Test Namessdfsd")
                .user(user)
                .build();

        when(accountRepository.save(any(Account.class))).thenReturn(accountReturn);

        doNothing().when(event).dispatch(accountReturn, AccountEvent.EventType.CREATED);

        Account savedAccount = accountService.save(accountToSave);
        assertNotNull(savedAccount.getId());

        verify(accountRepository).save(accountArgumentCaptor.capture());
        System.out.println(accountArgumentCaptor.getValue());
    }

    @Test
    @DisplayName("Should reject repeated account")
    void should_reject_repeated_account() {

        User user = UserBuilderTest.builder().build();

        Account accountToSave = Account.AccountBuilder
                .newAccount()
                .id(1L)
                .name("Test Name")
                .user(user)
                .build();


        when(accountRepository.getAccountsByUserId(accountToSave.getId()))
                .thenReturn(List.of(accountToSave));

        when(accountRepository.save(accountToSave)).thenReturn(accountToSave);

        String message = assertThrows(ValidationException.class, () ->
                accountService.save(accountToSave)).getMessage();

        assertEquals("Account already exists", message);

    }

    @Test
    @DisplayName("the account should be saved successfully even if there are other different accounts")
    void the_account_should_be_saved_successfully_even_if_there_are_other_different_accounts() throws Exception {

        User user = UserBuilderTest.builder().build();

        Account accountToSave = Account.AccountBuilder
                .newAccount()
                .id(1L)
                .name("Test Name")
                .user(user)
                .build();

        Account accountToSaved = Account.AccountBuilder
                .newAccount()
                .id(1L)
                .name("Test Name other")
                .user(user)
                .build();


        when(accountRepository.getAccountsByUserId(accountToSave.getId()))
                .thenReturn(List.of(accountToSaved));

        when(accountRepository.save(accountToSave)).thenReturn(accountToSave);

        Account account = accountService.save(accountToSave);

        assertEquals(accountToSave.getName(), account.getName());

    }

    @Test
    @DisplayName("shouldn't keep tho account without the event")
    void shouldnt_keep_tho_account_without_the_event() throws Exception {

        User user = UserBuilderTest.builder().build();

        Account accountToSave = Account.AccountBuilder
                .newAccount()
                .id(null)
                .name("Test Name")
                .user(user)
                .build();

        Account accountReturn = Account.AccountBuilder
                .newAccount()
                .id(1L)
                .name("Test Name")
                .user(user)
                .build();

        when(accountRepository.save(accountToSave)).thenReturn(accountReturn);
        doThrow(new Exception("Error saving account")).when(event).dispatch(accountReturn, AccountEvent.EventType.CREATED);

        String message = assertThrows(Exception.class, () ->
                accountService.save(accountToSave)).getMessage();

        assertEquals("Error saving account", message);

        verify(accountRepository).delete(accountReturn);

    }

}