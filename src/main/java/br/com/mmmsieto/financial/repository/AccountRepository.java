package br.com.mmmsieto.financial.repository;

import br.com.mmmsieto.financial.domain.entity.Account;

public interface AccountRepository {

    Account save(Account account);

}
