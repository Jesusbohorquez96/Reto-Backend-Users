package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.*;

import java.util.List;

public interface IUsersHandler {

    List<UserResponse> getFromUser();

    void deleteFromUser(Long userId);

    OwnerResponse getOwnerInfo(Long ownerId);

    AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest);

    void registerUser(RegisterRequest registerRequest);

    void registerEmployeeRest(UserEmployeeRequest userEmployeeRequest, Long userId);


    EmployeeRestaurantIdResponse getEmployeeInfo(Long employeeId);
}
