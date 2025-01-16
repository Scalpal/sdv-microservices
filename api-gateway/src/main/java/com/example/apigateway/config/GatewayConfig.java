package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("sdv-school-api", r -> r.path("/api/school", "/api/school/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://sdv-school-api"))
                .route("sdv-student-api", r -> r.path("/api/student", "/api/student/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://sdv-student-api"))
                .route("sdv-auth-api", r -> r.path("/api/auth", "/api/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://sdv-auth-api"))
                .build();
    }
}
