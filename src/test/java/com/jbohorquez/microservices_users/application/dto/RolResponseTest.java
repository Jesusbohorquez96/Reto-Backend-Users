package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RolResponseTest {

    @Test
    void testDefaultConstructor() {
        RolResponse rolResponse = new RolResponse();

        assertNull(rolResponse.getRolId());
        assertNull(rolResponse.getRolName());
        assertNull(rolResponse.getRolDescription());
    }

    @Test
    void testSettersAndGetters() {
        RolResponse rolResponse = new RolResponse();

        rolResponse.setRolId(1L);
        rolResponse.setRolName("Admin");
        rolResponse.setRolDescription("Administrator role");

        assertEquals(1L, rolResponse.getRolId());
        assertEquals("Admin", rolResponse.getRolName());
        assertEquals("Administrator role", rolResponse.getRolDescription());
    }
}
