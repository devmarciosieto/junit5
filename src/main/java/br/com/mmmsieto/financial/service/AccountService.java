package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.Account;
import br.com.mmmsieto.financial.repository.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

}
