package com.jbohorquez.microservices_users.infrastructure.exceptionhandler;

import com.jbohorquez.microservices_users.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.microservices_users.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.microservices_users.infrastructure.exception.NotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put(FIELD, fieldError.getField());
                    errorDetails.put(MESSAGE, fieldError.getDefaultMessage());
                    errorDetails.put(REJECTED_VALUE, fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : NULL);
                    return errorDetails;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put(ERRORS, errors);
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());
        response.put(MESSAGE, VALIDATION_FAILED);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String, String>> handleMalformedJwtException(MalformedJwtException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.FORBIDDEN.value()),
                        MESSAGE, INVALID_JWT
                ));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.CONFLICT.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.NOT_FOUND.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.NOT_FOUND.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        MESSAGE, INTERNAL_SERVER
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        STATUS, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        MESSAGE, OCCURRED_UNEXPECTED
                ));
    }
}
