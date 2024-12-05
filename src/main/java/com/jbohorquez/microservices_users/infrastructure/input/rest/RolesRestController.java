package com.jbohorquez.microservices_users.infrastructure.input.rest;

import com.jbohorquez.microservices_users.application.dto.RolRequest;
import com.jbohorquez.microservices_users.application.dto.RolResponse;
import com.jbohorquez.microservices_users.application.handler.IRolHandler;
import com.jbohorquez.microservices_users.infrastructure.exception.AllExistsException;
import com.jbohorquez.microservices_users.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@RestController
@RequestMapping(ROLES_API)
@RequiredArgsConstructor
@Tag(name = TAG_ROLES, description = API_ROLES)
public class RolesRestController {

    private final IRolHandler rolHandler;

    @Operation(summary = "Get all Roles", description = "Returns a list of all roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol list returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<List<RolResponse>> getAllRol() {
        List<RolResponse> rol = rolHandler.getAllRol();
        return ResponseEntity.ok(rol);
    }

    @Operation(summary = "Save a new rol", description = "Saves a new rol to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(ROOT)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Map<String, String>> saveInRol(@Valid @RequestBody RolRequest rolRequest) {
        try {
            rolHandler.saveInRol(rolRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
        } catch (AllExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap(MESSAGE, e.getMessage()));
        }
    }

    @Operation(summary = "Delete a rol", description = "Delete an existing rol based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Rol not found")
    })
    @DeleteMapping(ROL_ID)
    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Map<String, String>> deleteRol(@PathVariable (name = ROL_ID_TARGET) Long rolId) {
        rolHandler.deleteRol(rolId);
        return ResponseEntity.noContent().build();
    }
}
