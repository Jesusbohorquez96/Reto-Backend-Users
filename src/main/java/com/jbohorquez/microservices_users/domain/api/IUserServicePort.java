package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.OwnerResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.domain.model.User;

import java.util.List;

public interface IUserServicePort {

    List<User> getAllUser();

    void deleteUser(Long userId);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    void registerUser(RegisterRequest registerRequest);

    OwnerResponse findOwnerById(Long ownerId);
}

