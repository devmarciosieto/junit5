package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.Account;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.repository.AccountRepository;
import br.com.mmmsieto.financial.service.external.AccountEvent;

import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountEvent accountEvent;

    public AccountService(AccountRepository accountRepository,
                          AccountEvent accountEvent) {
        this.accountRepository = accountRepository;
        this.accountEvent = accountEvent;
    }

    public Account save(Account account) {

        List<Account> accounts = accountRepository.getAccountsByUserId(account.getId());

        accounts.stream().forEach(existingAccount -> {
            if(existingAccount.getName().equalsIgnoreCase(account.getName())) {
                throw new ValidationException("Account already exists");
            }
        });

        Account accountPersist = accountRepository.save(account);

        try {
            accountEvent.dispatch(accountPersist, AccountEvent.EventType.CREATED);
        } catch (Exception e) {
            accountRepository.delete(accountPersist);
            throw new RuntimeException("Error saving account");
        }

        return accountPersist;

    }

}
