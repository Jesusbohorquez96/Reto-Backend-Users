package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        template.header("Authorization", "Bearer " + token);
    }
}
