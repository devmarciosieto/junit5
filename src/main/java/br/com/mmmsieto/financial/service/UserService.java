package br.com.mmmsieto.financial.service;

import br.com.mmmsieto.financial.domain.entity.User;
import br.com.mmmsieto.financial.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        User userSave = userRepository.save(user);
        return userSave;
    }

}
