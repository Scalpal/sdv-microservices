package com.example.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {
    public static final List<String> notSecuredRoutes = List.of(
            "/api/auth/register",
            "/api/auth/login"
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            notSecuredRoutes.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
