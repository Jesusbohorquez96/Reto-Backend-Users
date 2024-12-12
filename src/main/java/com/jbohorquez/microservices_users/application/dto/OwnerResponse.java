package com.jbohorquez.microservices_users.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponse {

    private Long id;
    private String name;
}
