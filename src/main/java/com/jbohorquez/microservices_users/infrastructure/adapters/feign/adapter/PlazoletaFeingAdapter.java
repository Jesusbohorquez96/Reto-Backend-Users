package com.jbohorquez.microservices_users.infrastructure.adapters.feign.adapter;

import com.jbohorquez.microservices_users.application.dto.IdRestaurantResponse;
import com.jbohorquez.microservices_users.domain.spi.IPlazoletaPort;
import com.jbohorquez.microservices_users.infrastructure.adapters.feign.client.PlazoletaFeignClient;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlazoletaFeingAdapter implements IPlazoletaPort {

    private final PlazoletaFeignClient plazoletaFeignClient;

    @Override
    public Optional<IdRestaurantResponse> findRestaurantById(Long restaurantId) {
        IdRestaurantResponse restaurant = plazoletaFeignClient.findRestaurantById(restaurantId);
        return Optional.ofNullable(restaurant);
    }
}
