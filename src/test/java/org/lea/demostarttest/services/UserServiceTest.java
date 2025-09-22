package org.lea.demostarttest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lea.demostarttest.dtos.UserDto;
import org.lea.demostarttest.entities.User;
import org.lea.demostarttest.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserServiceFunctionality() {

        User user = new User(null, "Juan Perez", "juan@example.com");
        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        Mono<User> result = userService.saveUser(user);

        StepVerifier.create(result)
                .expectNextMatches(u -> u.getName().equals("Juan Perez")
                        && u.getEmail().equals("juan@example.com"))
                .verifyComplete();

    }

    @Test
    void testUserServiceFind() {
        User user = new User(null, "Juan Perez", "juan@example.com");
        when(userRepository.findById(1L)).thenReturn(Mono.just(user));

        Mono<UserDto> result = userService.findById(1L);

        StepVerifier.create(result)
                .expectNextMatches(u -> u.name().equals("Juan Perez")
                        && u.email().equals("juan@example.com"))
                .verifyComplete();

    }
}