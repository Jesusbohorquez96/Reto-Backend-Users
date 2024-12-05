package com.jbohorquez.microservices_users.infrastructure.output.jpa.adapter;

import com.jbohorquez.microservices_users.domain.model.User;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userJpaAdapter = new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Test
    void testGetAllUser_ReturnsUsers() {
        List<UserEntity> userEntities = List.of(
                new UserEntity(1L, "John", "Doe", "john@example.com", "password", "123456789"),
                new UserEntity(2L, "Jane", "Smith", "jane@example.com", "password", "987654321")
        );

        List<User> users = List.of(
                new User(1L, "John", "Doe", "john@example.com", "password", "123456789"),
                new User(2L, "Jane", "Smith", "jane@example.com", "password", "987654321")
        );

        when(userRepository.findAll()).thenReturn(userEntities);
        when(userEntityMapper.toUserList(userEntities)).thenReturn(users);
        List<User> result = userJpaAdapter.getAllUser();
        assertEquals(2, result.size());
        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
        verify(userEntityMapper, times(1)).toUserList(userEntities);
    }

    @Test
    void testGetAllUser_ThrowsNoDataFoundException() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, userJpaAdapter::getAllUser);
        verify(userRepository, times(1)).findAll();
        verifyNoInteractions(userEntityMapper);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        userJpaAdapter.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
