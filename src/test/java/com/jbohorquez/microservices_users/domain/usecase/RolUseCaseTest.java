package com.jbohorquez.microservices_users.domain.usecase;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.domain.spi.RolPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolUseCaseTest {

    @Mock
    private RolPersistencePort rolPersistencePort;

    private RolUseCase rolUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolUseCase = new RolUseCase(rolPersistencePort);
    }

    @Test
    void testSaveRol_NameIsNull() {
        Rol rol = new Rol(1L, null, "Valid description");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rolUseCase.saveRol(rol));
        assertEquals(NAME_REQUIRED, exception.getMessage());
        verifyNoInteractions(rolPersistencePort);
    }

    @Test
    void testSaveRol_NameIsEmpty() {
        Rol rol = new Rol(1L, "", "Valid description");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rolUseCase.saveRol(rol));
        assertEquals(NAME_REQUIRED, exception.getMessage());
        verifyNoInteractions(rolPersistencePort);
    }

    @Test
    void testGetAllRol() {
        List<Rol> mockRoles = List.of(
                new Rol(1L, "Admin", "Administrator role"),
                new Rol(2L, "User", "Standard user role")
        );
        when(rolPersistencePort.getAllRol()).thenReturn(mockRoles);
        List<Rol> result = rolUseCase.getAllRol();
        assertEquals(2, result.size());
        verify(rolPersistencePort, times(1)).getAllRol();
    }

    @Test
    void testDeleteRol() {
        Long rolId = 1L;
        rolUseCase.deleteRol(rolId);
        verify(rolPersistencePort, times(1)).deleteRol(rolId);
    }
}
