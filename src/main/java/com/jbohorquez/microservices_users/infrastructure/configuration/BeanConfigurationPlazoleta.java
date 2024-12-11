package com.jbohorquez.microservices_users.infrastructure.configuration;

import com.jbohorquez.microservices_users.domain.spi.IPlazoletaPort;
import com.jbohorquez.microservices_users.infrastructure.adapters.feign.adapter.PlazoletaFeingAdapter;
import com.jbohorquez.microservices_users.infrastructure.adapters.feign.client.PlazoletaFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationPlazoleta {

    private final PlazoletaFeignClient plazoletaFeignClient;

    @Bean
    public IPlazoletaPort plazoletaPort() {
        return new PlazoletaFeingAdapter(plazoletaFeignClient);
    }
}
