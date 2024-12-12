package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersRestControllerTest {

    private IUsersHandler usersHandler;
    private UsersRestController usersRestController;

    @BeforeEach
    void setUp() {
        usersHandler = mock(IUsersHandler.class);
        usersRestController = new UsersRestController(usersHandler);
    }

    @Test
    void getFromUser_shouldReturnListOfUsers() {
        List<UserResponse> userList = List.of(new UserResponse());
        when(usersHandler.getFromUser()).thenReturn(userList);

        ResponseEntity<List<UserResponse>> response = usersRestController.getFromUser();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userList, response.getBody());
        verify(usersHandler, times(1)).getFromUser();
    }

    @Test
    void deleteFromUser_shouldCallHandler() {
        ResponseEntity<Void> response = usersRestController.deleteFromUser(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(usersHandler, times(1)).deleteFromUser(1L);
    }

    @Test
    void validateOwner_shouldReturnOwnerResponseIfExists() {
        OwnerResponse ownerResponse = new OwnerResponse();
        when(usersHandler.getOwnerInfo(1L)).thenReturn(ownerResponse);

        ResponseEntity<OwnerResponse> response = usersRestController.validateOwner(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ownerResponse, response.getBody());
        verify(usersHandler, times(1)).getOwnerInfo(1L);
    }

    @Test
    void validateOwner_shouldReturnNotFoundIfNotExists() {
        when(usersHandler.getOwnerInfo(1L)).thenReturn(null);

        ResponseEntity<OwnerResponse> response = usersRestController.validateOwner(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(usersHandler, times(1)).getOwnerInfo(1L);
    }

    @Test
    void validateEmployee_shouldReturnEmployeeResponseIfExists() {
        EmployeeRestaurantIdResponse employeeResponse = new EmployeeRestaurantIdResponse();
        when(usersHandler.getEmployeeInfo(1L)).thenReturn(employeeResponse);

        ResponseEntity<EmployeeRestaurantIdResponse> response = usersRestController.validateEmployee(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employeeResponse, response.getBody());
        verify(usersHandler, times(1)).getEmployeeInfo(1L);
    }

    @Test
    void validateEmployee_shouldReturnNotFoundIfNotExists() {
        when(usersHandler.getEmployeeInfo(1L)).thenReturn(null);

        ResponseEntity<EmployeeRestaurantIdResponse> response = usersRestController.validateEmployee(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(usersHandler, times(1)).getEmployeeInfo(1L);
    }
}
