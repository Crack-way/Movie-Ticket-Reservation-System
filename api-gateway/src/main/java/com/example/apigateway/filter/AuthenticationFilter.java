package com.example.apigateway.filter;


import com.example.apigateway.RouteValidator;
import com.example.apigateway.exception.DoesNotContainAuthorizationException;
import com.example.apigateway.exception.TokenInvalidException;
import com.example.apigateway.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {

        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new DoesNotContainAuthorizationException("No authorization");
                }

                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeaders != null && authHeaders.startsWith("Bearer")) {

                    authHeaders = authHeaders.substring(7);
                }

                try {

                    jwtService.isTokenValid(authHeaders);

                } catch (Exception e) {

                    throw new TokenInvalidException("Token not valid");
                }


            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {


    }

}


