package com.jbohorquez.microservices_users.infrastructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    CATEGORY_NOT_FOUND("No Category was found with that name"),
    ALREADY_EXISTS("That name already exists"),
    NO_DATA_FOUND("No data found for the requested petition"),
    INTERNAL_ERROR("internal error"),
    NOT_EXISTS("Not exist"),
    SUCCESSFUL_CREATION("Successful creation"),
    CHARACTER_LIMIT_EXCEEDED("Character limit exceeded"),
    INVALID("Invalid password or email"),
    ACCESS_DENIED("Access denied: Invalid or malformed JWT token"),
    EXIT("Successful operation"),;

    private final String message;
    ExceptionResponse(String message) {
        this.message = message;
    }
}