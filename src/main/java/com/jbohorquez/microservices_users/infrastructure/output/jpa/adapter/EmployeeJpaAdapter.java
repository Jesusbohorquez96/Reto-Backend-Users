package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.Employee;
import com.jbohorquez.microservices_users.domain.spi.EmployeePersistencePort;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.EmployeeEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeJpaAdapter implements EmployeePersistencePort {

    private final IEmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public void registerEmployeeRest(Employee employee) {
        employeeRepository.save(employeeEntityMapper.toEntity(employee));
    }

    @Override
    public Employee findById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findByUserEntityId(employeeId);
        return employeeEntityMapper.toDomain(employeeEntity);

    }
}
