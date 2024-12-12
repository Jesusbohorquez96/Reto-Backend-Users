package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.*;
import com.jbohorquez.microservices_users.application.handler.IUsersHandler;
import com.jbohorquez.microservices_users.infrastructure.exceptionhandler.ExceptionResponse;
import com.jbohorquez.microservices_users.infrastructure.output.jpa.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@RestController
@RequestMapping(USERS_API)
@RequiredArgsConstructor
@Tag(name = TAG_USERS, description = API_USERS)
public class UsersRestController {

    private final IUsersHandler usersHandler;

    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User list returned successfully")
    })
    @GetMapping(ROOT)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<List<UserResponse>> getFromUser() {
        return ResponseEntity.ok(usersHandler.getFromUser());
    }

    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_ADMIN)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(ADMIN);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }

    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_CUSTOMER)
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(CUSTOMER);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }
    
    @Operation(summary = "Save a new user", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_OWNER)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<?> registerOwner(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.setRol(OWNER);
        usersHandler.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }

    @Operation(summary = "Save a new user employee", description = "Saves a new user to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping(REGISTER_EMPLOYEE)
    @PreAuthorize(ROL_OWNER)
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody UserEmployeeRequest userEmployeeRequest) {
        userEmployeeRequest.setRol(EMPLOYEE);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserEntity) authentication.getPrincipal()).getId();
        usersHandler.registerEmployeeRest(userEmployeeRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
    }

    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(USER_ID)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Void> deleteFromUser(@PathVariable Long userId) {
        usersHandler.deleteFromUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Validate Owner ID", description = "Validates if a user with the given ID has the role of owner.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner ID is valid"),
            @ApiResponse(responseCode = "404", description = "Owner ID is not valid")
    })
    @GetMapping(VALIDATE_OWNER)
    public ResponseEntity<OwnerResponse> validateOwner(@PathVariable Long ownerId) {
        OwnerResponse ownerResponse = usersHandler.getOwnerInfo(ownerId);
        if (ownerResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ownerResponse);
    }

    //validateEmployee
    @Operation(summary = "Validate Employee ID", description = "Validates if a user with the given ID has the role of employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee ID is valid"),
            @ApiResponse(responseCode = "404", description = "Employee ID is not valid")
    })
    @GetMapping("/validate-employee/{employeeId}")
    public ResponseEntity<EmployeeRestaurantIdResponse> validateEmployee(@PathVariable Long employeeId) {
        EmployeeRestaurantIdResponse employeeRestaurantIdResponse = usersHandler.getEmployeeInfo(employeeId);
        if (employeeRestaurantIdResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employeeRestaurantIdResponse);
    }
}