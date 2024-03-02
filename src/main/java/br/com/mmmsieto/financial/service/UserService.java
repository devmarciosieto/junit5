package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.domain.exceptions.ValidationException;
import br.com.mmmsieto.financial.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {

        userRepository.getUserByEmail(user.getEmail()).ifPresent(userOptional -> {
            throw new ValidationException(String.format("There is already a user with this emial: %s registered", user.getEmail()));
        });

        User userSave = userRepository.save(user);
        return userSave;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}
