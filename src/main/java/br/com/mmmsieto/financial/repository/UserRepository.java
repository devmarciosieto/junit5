package br.com.mmmsieto.financial.repository;

import br.com.mmmsieto.financial.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> getUserByEmail(String email);

}
