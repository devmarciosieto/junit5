package br.com.mmmsieto.financial.repository;

import br.com.mmmsieto.financial.domain.entity.Account;

import java.util.List;

public interface AccountRepository {

    Account save(Account account);

    List<Account> getAccountsByUserId(Long userId);

    void delete(Account account);

}
