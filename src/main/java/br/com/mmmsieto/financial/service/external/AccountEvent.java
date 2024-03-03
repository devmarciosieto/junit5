package br.com.mmmsieto.financial.service.external;

import br.com.mmmsieto.financial.domain.entity.Account;

public interface AccountEvent {

    enum EventType {CREATED, UPDATED, DELETED}

    void dispatch(Account account, EventType type) throws Exception;;

}
