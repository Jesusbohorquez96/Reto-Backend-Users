package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.Rol;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.RolEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IRolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RolJpaAdapterTest {

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private RolEntityMapper rolEntityMapper;

    private RolJpaAdapter rolJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rolJpaAdapter = new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Test
    void testSaveRol() {
        Rol rol = new Rol(1L, "Admin", "Administrator role");
        RolEntity rolEntity = new RolEntity(1L, "Admin", "Administrator role");

        when(rolEntityMapper.toEntity(rol)).thenReturn(rolEntity);
        rolJpaAdapter.saveRol(rol);
        verify(rolEntityMapper, times(1)).toEntity(rol);
        verify(rolRepository, times(1)).save(rolEntity);
    }

    @Test
    void testGetAllRol_ReturnsRoles() {
        List<RolEntity> rolEntities = List.of(
                new RolEntity(1L, "Admin", "Administrator role"),
                new RolEntity(2L, "User", "Standard user role")
        );

        List<Rol> roles = List.of(
                new Rol(1L, "Admin", "Administrator role"),
                new Rol(2L, "User", "Standard user role")
        );

        when(rolRepository.findAll()).thenReturn(rolEntities);
        when(rolEntityMapper.toRolList(rolEntities)).thenReturn(roles);
        List<Rol> result = rolJpaAdapter.getAllRol();
        assertEquals(2, result.size());
        assertEquals(roles, result);
        verify(rolRepository, times(1)).findAll();
        verify(rolEntityMapper, times(1)).toRolList(rolEntities);
    }

    @Test
    void testGetAllRol_ThrowsNoDataFoundException() {
        when(rolRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, rolJpaAdapter::getAllRol);
        verify(rolRepository, times(1)).findAll();
        verifyNoInteractions(rolEntityMapper);
    }

    @Test
    void testDeleteRol() {
        Long rolId = 1L;
        rolJpaAdapter.deleteRol(rolId);
        verify(rolRepository, times(1)).deleteById(rolId);
    }
}
