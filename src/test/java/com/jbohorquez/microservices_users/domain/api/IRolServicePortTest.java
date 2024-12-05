package com.jbohorquez.microservices_users.domain.api;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import com.jbohorquez.microservices_users.domain.usecase.RolUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IRolServicePortTest {

    @Mock
    private RolPersistencePort rolPersistencePort;

    @InjectMocks
    private RolUseCase rolUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllRol_NoRolesFound() {
        when(rolPersistencePort.getAllRol()).thenReturn(List.of());
        List<Rol> roles = rolUseCase.getAllRol();
        assertNotNull(roles);
        assertTrue(roles.isEmpty());
        verify(rolPersistencePort, times(1)).getAllRol();
    }

    @Test
    public void testDeleteRol() {
        Long rolId = 1L;
        rolUseCase.deleteRol(rolId);
        verify(rolPersistencePort, times(1)).deleteRol(rolId);
    }
}
