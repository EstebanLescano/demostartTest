package org.lea.demostarttest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lea.demostarttest.dtos.UserDto;
import org.lea.demostarttest.entities.User;
import org.lea.demostarttest.repositories.UserRepository;
import org.lea.demostarttest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@DataR2dbcTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGuardarYBuscarPorId() {
        User user = new User(null, "Juan Perez", "juan@example.com");

        Mono<User> flujo = userRepository.save(user)
                .flatMap(guardado -> userRepository.findById(guardado.getId()));

        StepVerifier.create(flujo)
                .expectNextMatches(u -> u.getName().equals("Juan Perez")
                        && u.getEmail().equals("juan@example.com"))
                .verifyComplete();
    }


}
