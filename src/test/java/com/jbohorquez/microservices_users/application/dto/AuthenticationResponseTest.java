package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationResponseTest {

    @Test
    void testBuilder() {
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("sampleToken123")
                .build();
        assertEquals("sampleToken123", response.getToken());
    }

    @Test
    void testNoArgsConstructor() {
        AuthenticationResponse response = new AuthenticationResponse();
        assertNull(response.getToken());
        response.setToken("anotherToken456");
        assertEquals("anotherToken456", response.getToken());
    }

    @Test
    void testAllArgsConstructor() {
        AuthenticationResponse response = new AuthenticationResponse("finalToken789");
        assertEquals("finalToken789", response.getToken());
    }

    @Test
    void testSetterGetter() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("myToken");
        assertEquals("myToken", response.getToken());
    }
}