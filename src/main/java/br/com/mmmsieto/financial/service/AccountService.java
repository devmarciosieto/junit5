package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.Account;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.repository.AccountRepository;

import java.util.List;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {

        List<Account> accounts = accountRepository.getAccountsByUserId(account.getId());

        accounts.stream().forEach(existingAccount -> {
            if(existingAccount.getName().equalsIgnoreCase(account.getName())) {
                throw new ValidationException("Account already exists");
            }
        });

        return accountRepository.save(account);
    }

}
