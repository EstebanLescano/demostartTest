package org.lea.demostarttest.services;

import org.lea.demostarttest.dtos.UserDto;
import org.lea.demostarttest.entities.User;
import org.lea.demostarttest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()));
    }

    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }
}
