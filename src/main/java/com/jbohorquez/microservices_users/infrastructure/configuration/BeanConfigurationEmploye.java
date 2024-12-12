package com.jbohorquez.microservices_users.infrastructure.configuration;

import com.jbohorquez.microservices_users.domain.spi.EmployeePersistencePort;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter.EmployeeJpaAdapter;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationEmploye {

    private final IEmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Bean
    public EmployeePersistencePort employeePersistencePort() {
        return new EmployeeJpaAdapter(employeeRepository, employeeEntityMapper);
    }
}

