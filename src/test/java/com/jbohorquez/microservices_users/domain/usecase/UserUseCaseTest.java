package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.application.dto.AuthenticationRequest;
import com.jbohorquez.microservices_users.application.dto.AuthenticationResponse;
import com.jbohorquez.microservices_users.application.dto.RegisterRequest;
import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.domain.spi.UserPersistencePort;
import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.IAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private IAuthenticationService authenticationService;

    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort, authenticationService) {};
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = List.of(
                new User(1L, "John", "Doe", "john@example.com", "12345", "password")
        );
        when(userPersistencePort.getAllUser()).thenReturn(mockUsers);
        List<User> result = userUseCase.getAllUser();

        assertEquals(1, result.size());
        verify(userPersistencePort, times(1)).getAllUser();
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        userUseCase.deleteUser(userId);
        verify(userPersistencePort, times(1)).deleteUser(userId);
    }

    @Test
    void testValidateUser() {
        AuthenticationRequest request = new AuthenticationRequest("john@example.com", "password");
        AuthenticationResponse mockResponse = new AuthenticationResponse("token123");
        when(authenticationService.authenticate(request)).thenReturn(mockResponse);
        AuthenticationResponse response = userUseCase.validateUser(request);

        assertNotNull(response);
        assertEquals("token123", response.getToken());
        verify(authenticationService, times(1)).authenticate(request);
    }


    @Test
    void testRegisterUser_MissingMandatoryField() {
        RegisterRequest request = new RegisterRequest(
                null, "Doe", "john@example.com", "password", "123456789", "2000-01-01", 12345L
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userUseCase.registerUser(request));
        assertEquals("Name is required", exception.getMessage());
    }

}
