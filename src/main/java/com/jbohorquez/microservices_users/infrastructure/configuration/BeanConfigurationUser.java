package com.jbohorquez.microservices_users.infrastructure.configuration;

import com.jbohorquez.microservices_users.domain.api.IUserServicePort;
import com.jbohorquez.microservices_users.domain.spi.EmployeePersistencePort;
import com.jbohorquez.microservices_users.domain.spi.IPlazoletaPort;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.domain.usecase.UserUseCase;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationUser {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final EmployeePersistencePort employeePersistencePort;
    private final IPlazoletaPort plazoletaPort;
    private final IAuthenticationService authenticationService;

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(UserPersistencePort userPersistencePort) {
        return new UserUseCase(
                userPersistencePort,
                authenticationService,
                employeePersistencePort,
                plazoletaPort
        ) {
        };
    }
}