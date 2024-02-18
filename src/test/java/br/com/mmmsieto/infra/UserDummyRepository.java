package br.com.mmmsieto.infra;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;

import java.util.Optional;

import static br.com.mmmsieto.builders.UserBuilderTest.builder;


public class UserDummyRepository implements UserRepository {

    @Override
    public User save(User user) {
        return builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        if("email@gmail.com".equals(email)) {
            return Optional.of(builder().email(email).build());
        }

        return Optional.empty();
    }

}
