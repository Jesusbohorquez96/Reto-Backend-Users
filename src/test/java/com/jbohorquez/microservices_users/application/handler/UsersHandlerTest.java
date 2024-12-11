package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.application.mapper.UserResponseMapper;
import com.jbohorquez.microservices_users.domain.api.IUserServicePort;
import com.jbohorquez.microservices_users.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersHandlerTest {

    @Mock
    private UserResponseMapper userResponseMapper;

    @Mock
    private IUserServicePort userServicePort;


    private UsersHandler usersHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usersHandler = new UsersHandler(userResponseMapper, userServicePort);
    }

    @Test
    void testGetFromUser() {
        List<User> users = List.of(
                new User(1L, "John", "Doe", "john@example.com", "password", "123456789"),
                new User(2L, "Jane", "Smith", "jane@example.com", "password", "987654321")
        );

        List<UserResponse> responses = List.of(
                new UserResponse(1L, "John", "Doe", "john@example.com", "123456789"),
                new UserResponse(2L, "Jane", "Smith", "jane@example.com", "987654321")
        );

        when(userServicePort.getAllUser()).thenReturn(users);
        when(userResponseMapper.toResponseList(any(User.class)))
                .thenReturn(responses.get(0), responses.get(1));
        List<UserResponse> result = usersHandler.getFromUser();
        assertEquals(2, result.size());
        assertEquals(responses, result);
        verify(userServicePort, times(1)).getAllUser();
        verify(userResponseMapper, times(2)).toResponseList(any(User.class));
    }

    @Test
    void testDeleteFromUser() {
        Long userId = 1L;
        usersHandler.deleteFromUser(userId);
        verify(userServicePort, times(1)).deleteUser(userId);
    }

    @Test
    void testValidateUser() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("john@example.com", "password");
        AuthenticationResponse mockResponse = new AuthenticationResponse("valid-token");

        when(userServicePort.validateUser(authenticationRequest)).thenReturn(mockResponse);
        AuthenticationResponse result = usersHandler.validateUser(authenticationRequest);
        assertNotNull(result);
        assertEquals("valid-token", result.getToken());
        verify(userServicePort, times(1)).validateUser(authenticationRequest);
    }

    @Test
    void testRegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest(
                "John",
                "Doe",
                "john@example.com",
                "password",
                "123456789",
                "2000-01-01",
                12345L
        );
        usersHandler.registerUser(registerRequest);
        verify(userServicePort, times(1)).registerUser(registerRequest);
    }
}
