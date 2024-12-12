package com.jbohorquez.microservices_users.infrastructure.output.jpa.repository;

import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByUserEntityId(Long userId);
}
