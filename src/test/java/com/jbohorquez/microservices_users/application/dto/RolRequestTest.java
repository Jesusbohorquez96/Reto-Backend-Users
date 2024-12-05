package com.jbohorquez.microservices_users.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class RolRequestTest {

    private Validator validator;
    private RolRequest rolRequest;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        rolRequest = new RolRequest();
        rolRequest.setName("Admin");
        rolRequest.setDescription("Administrator role");
    }

    @Test
    void testValidRolRequest() {
        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNameCannotBeBlank() {
        rolRequest.setName("");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals(NAME_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    void testDescriptionCannotBeBlank() {
        rolRequest.setDescription("");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals(DESCRIPTION_REQUIRED, violations.iterator().next().getMessage());
    }

    @Test
    void testDescriptionMaxLengthExceeded() {
        rolRequest.setDescription("A".repeat(MAX_LENGTH + 1)); // Excede el tamaño máximo

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals(DESCRIPTION_MAX_LENGTH_EXCEEDED, violations.iterator().next().getMessage());
    }

    @Test
    void testNameAndDescriptionBothBlank() {
        rolRequest.setName("");
        rolRequest.setDescription("");

        Set<ConstraintViolation<RolRequest>> violations = validator.validate(rolRequest);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(NAME_REQUIRED)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(DESCRIPTION_REQUIRED)));
    }
}
