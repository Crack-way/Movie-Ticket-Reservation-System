package com.example.apigateway;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> opendApiEndpoinsts = List.of(
            "/auth-api/authenticate",
            "auth-api/register",
            "/eureka"

    );



    public Predicate<ServerHttpRequest> isSecured =

            request -> opendApiEndpoinsts.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));



}
