package br.com.mmmsieto.financial.infra;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;

import java.util.*;

public class UserMemoryRepository implements UserRepository {

    private List<User> users;
    private Long currentId;

    public UserMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        save(new User(nextId(), "User name", "user@gmail.com", "123456"));
    }

    @Override
    public User save(User user) {
        User newUser = new User(nextId(), user.getName(), user.getEmail(), user.getPassword());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId() {
        return currentId++;
    }

}
