package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.domain.model.Employee;
public interface EmployeePersistencePort {

    void registerEmployeeRest(Employee employee);

    Employee findById(Long employeeId);
}
