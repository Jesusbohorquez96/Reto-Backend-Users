package com.jbohorquez.microservices_users.domain.spi;

import com.jbohorquez.microservices_users.application.dto.IdRestaurantResponse;

import java.util.Optional;

public interface IPlazoletaPort {

    Optional<IdRestaurantResponse> findRestaurantById(Long ownerId);
}
