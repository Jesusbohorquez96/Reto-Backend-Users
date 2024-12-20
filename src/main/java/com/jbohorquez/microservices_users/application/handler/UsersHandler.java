package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.application.mapper.UserRequestMapper;
import com.jbohorquez.microservices_users.application.mapper.UserResponseMapper;
import com.jbohorquez.microservices_users.domain.api.IUserServicePort;
import com.jbohorquez.microservices_users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersHandler implements IUsersHandler {

    private final UserResponseMapper userResponseMapper;
    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;

    @Override
    public List<UserResponse> getFromUser() {
        List<User> users = userServicePort.getAllUser();
        return users.stream()
                .map(userResponseMapper::toResponseList)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFromUser(Long userId) {
        userServicePort.deleteUser(userId);
    }

    @Override
    public AuthenticationResponse validateUser(AuthenticationRequest authenticationRequest) {
        return userServicePort.validateUser(authenticationRequest);
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        userServicePort.registerUser(registerRequest);
    }

    @Override
    public void registerEmployeeRest(UserEmployeeRequest userEmployeeRequest, Long userId) {
        userServicePort.registerEmployeeRest(userEmployeeRequest, userId);
    }

    @Override
    public EmployeeRestaurantIdResponse getEmployeeInfo(Long employeeId) {
      return userResponseMapper.toEmployeeRestaurantIdResponse(userServicePort.findEmployeeId(employeeId));
    }

    @Override
    public OwnerResponse getOwnerInfo(Long ownerId) {
        return userServicePort.findOwnerById(ownerId);
    }
}
