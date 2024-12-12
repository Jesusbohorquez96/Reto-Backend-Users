package com.jbohorquez.microservices_users.application.dto;

import lombok.*;

import javax.validation.constraints.*;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class UserEmployeeRequest extends RegisterRequest {

    @NotNull(message = EMPLOYEE_ID_REQUIRED)
    private Long restaurantId;
}
