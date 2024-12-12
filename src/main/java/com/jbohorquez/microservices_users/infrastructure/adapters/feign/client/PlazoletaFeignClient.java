package com.jbohorquez.microservices_users.infrastructure.adapters.feign.client;

import com.jbohorquez.microservices_users.application.dto.IdRestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "plazoleta-service", url = "http://localhost:8086/restaurants")
public interface PlazoletaFeignClient {

    @GetMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    IdRestaurantResponse findRestaurantById(@PathVariable Long restaurantId);
}
