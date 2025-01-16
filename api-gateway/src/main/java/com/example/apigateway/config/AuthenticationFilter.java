package com.example.apigateway.config;

import com.example.apigateway.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.core.io.buffer.DataBuffer;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if(routerValidator.isSecured.test(request)){
            if(authMissing(request)) {
                return onError(exchange, "You're not authenticated.", HttpStatus.UNAUTHORIZED);
            }

            final String token = request.getHeaders().getOrEmpty("authorization").get(0);

            if(!jwtUtils.isTokenValid(token)) {
                return onError(exchange, "Token invalid.", HttpStatus.UNAUTHORIZED);
            }

            final String tokenWithoutBearer = token.split(" ")[1];

            if(jwtUtils.isExpired(tokenWithoutBearer)) {
                return onError(exchange, "Token expired.", HttpStatus.UNAUTHORIZED);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json");

        String errorResponse = String.format("{\"message\": \"%s\", \"status\": %d}", message, httpStatus.value());
        DataBuffer dataBuffer = response.bufferFactory().wrap(errorResponse.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    public boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("authorization");
    }
}
