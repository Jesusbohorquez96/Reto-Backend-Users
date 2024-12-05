package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    @Test
    void testBuilder() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("john@example.com")
                .password("securePassword123")
                .build();

        assertEquals("john@example.com", request.getEmail());
        assertEquals("securePassword123", request.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        AuthenticationRequest request = new AuthenticationRequest();

        assertNull(request.getEmail());
        assertNull(request.getPassword());

        request.setEmail("jane@example.com");
        request.setPassword("strongPassword456");

        assertEquals("jane@example.com", request.getEmail());
        assertEquals("strongPassword456", request.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        AuthenticationRequest request = new AuthenticationRequest("john@example.com", "password123");

        assertEquals("john@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void testSetterGetter() {
        AuthenticationRequest request = new AuthenticationRequest();

        request.setEmail("example@example.com");
        request.setPassword("mySecurePassword");

        assertEquals("example@example.com", request.getEmail());
        assertEquals("mySecurePassword", request.getPassword());
    }
}