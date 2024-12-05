package com.jbohorquez.microservices_users.application.handler;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.application.mapper.RolRequestMapper;
import com.jbohorquez.microservices_users.application.mapper.RolResponseMapper;
import com.jbohorquez.microservices_users.domain.api.IRolServicePort;
import com.jbohorquez.microservices_users.domain.model.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolHandlerTest {

    @Mock
    private RolRequestMapper rolRequestMapper;

    @Mock
    private RolResponseMapper rolResponseMapper;

    @Mock
    private IRolServicePort rolServicePort;

    private RolHandler rolHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolHandler = new RolHandler(rolRequestMapper, rolResponseMapper, rolServicePort);
    }

    @Test
    void testSaveInRol() {
        RolRequest rolRequest = new RolRequest("Admin", "Administrator role");
        Rol rol = new Rol(1L, "Admin", "Administrator role");

        when(rolRequestMapper.toRol(rolRequest)).thenReturn(rol);
        rolHandler.saveInRol(rolRequest);
        verify(rolRequestMapper, times(1)).toRol(rolRequest);
        verify(rolServicePort, times(1)).saveRol(rol);
    }

    @Test
    void testGetAllRol() {
        // Arrange
        List<Rol> roles = List.of(
                new Rol(1L, "Admin", "Administrator role"),
                new Rol(2L, "User", "Standard user role")
        );
        List<RolResponse> responses = List.of(
                new RolResponse(1L, "Admin", "Administrator role"),
                new RolResponse(2L, "User", "Standard user role")
        );

        when(rolServicePort.getAllRol()).thenReturn(roles);
        when(rolResponseMapper.toResponse(any(Rol.class)))
                .thenReturn(responses.get(0), responses.get(1));

        List<RolResponse> result = rolHandler.getAllRol();
        assertEquals(2, result.size());
        assertEquals(responses, result);
        verify(rolServicePort, times(1)).getAllRol();
        verify(rolResponseMapper, times(2)).toResponse(any(Rol.class));
    }

    @Test
    void testDeleteRol() {
        Long rolId = 1L;
        rolHandler.deleteRol(rolId);
        verify(rolServicePort, times(1)).deleteRol(rolId);
    }
}
