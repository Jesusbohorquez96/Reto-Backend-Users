package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.application.dto.OwnerResponse;
import com.jbohorquez.microservices_users.domain.model.User;

import java.util.List;

public interface UserPersistencePort {

        List<User> getAllUser();

        void deleteUser(Long userId);

        OwnerResponse findOwnerById(Long ownerId);

}

