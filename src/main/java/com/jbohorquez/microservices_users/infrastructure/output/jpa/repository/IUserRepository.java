package com.jbohorquez.microservices_users.infrastructure.output.jpa.repository;

import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long userId);

    void deleteById(Long userId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByIdAndRolId(Long id, Long rolId);
}
