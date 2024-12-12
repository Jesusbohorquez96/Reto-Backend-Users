package com.jbohorquez.microservices_users.infrastructure.adapters.feign.client;

import com.jbohorquez.microservices_users.application.dto.IdRestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@FeignClient(name = MICROSERVICE_PLAZOLETA, url = HTTP_RESTAURANTS)
public interface PlazoletaFeignClient {

    @GetMapping(value = EMPLOYEE_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    IdRestaurantResponse findRestaurantById(@PathVariable Long restaurantId);
}
