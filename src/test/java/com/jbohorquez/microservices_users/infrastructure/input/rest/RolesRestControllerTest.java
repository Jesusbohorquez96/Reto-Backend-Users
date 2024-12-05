package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.application.handler.IRolHandler;
import com.jbohorquez.microservices_users.infrastructure.exception.AllExistsException;
import com.jbohorquez.microservices_users.infrastructure.exceptionhandler.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RolesRestControllerTest {

    @InjectMocks
    private RolesRestController rolesRestController;

    @Mock
    private IRolHandler rolHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllRol_ShouldReturnOkWithListOfRoles() {
        List<RolResponse> rolesList = Collections.singletonList(new RolResponse());
        when(rolHandler.getAllRol()).thenReturn(rolesList);

        ResponseEntity<List<RolResponse>> response = rolesRestController.getAllRol();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolesList, response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveInRol_ShouldReturnCreatedWhenRolSavedSuccessfully() {
        RolRequest rolRequest = new RolRequest();
        rolRequest.setName("ADMIN");
        doNothing().when(rolHandler).saveInRol(rolRequest);

        ResponseEntity<Map<String, String>> response = rolesRestController.saveInRol(rolRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()), response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveInRol_ShouldReturnBadRequestWhenRolAlreadyExists() {
        RolRequest rolRequest = new RolRequest();
        rolRequest.setName("ADMIN");
        doThrow(new AllExistsException("Role already exists")).when(rolHandler).saveInRol(rolRequest);

        ResponseEntity<Map<String, String>> response = rolesRestController.saveInRol(rolRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonMap(MESSAGE, "Role already exists"), response.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteRol_ShouldReturnNoContentWhenRolDeletedSuccessfully() {
        Long rolId = 1L;
        doNothing().when(rolHandler).deleteRol(rolId);

        ResponseEntity<Map<String, String>> response = rolesRestController.deleteRol(rolId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
