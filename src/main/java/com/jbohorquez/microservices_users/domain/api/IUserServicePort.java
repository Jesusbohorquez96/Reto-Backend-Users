package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.domain.model.Employee;
import com.jbohorquez.microservices_users.domain.model.User;
import java.util.List;

public interface IUserServicePort {

    List<User> getAllUser();

    void deleteUser(Long userId);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    void registerUser(RegisterRequest registerRequest);

    OwnerResponse findOwnerById(Long ownerId);

    void registerEmployeeRest(UserEmployeeRequest userEmployeeRequest, Long userId );

    Employee findEmployeeId(Long employeeId);
}

