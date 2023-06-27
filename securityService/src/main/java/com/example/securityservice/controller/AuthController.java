package com.example.securityservice.controller;

import com.example.securityservice.dto.JwtResponse;
import com.example.securityservice.dto.RefreshTokenRequest;
import com.example.securityservice.dto.UserResponse;
import com.example.securityservice.entity.RefreshToken;
import com.example.securityservice.entity.UserCredentials;
import com.example.securityservice.exception.TokenAlreadyExpiredException;
import com.example.securityservice.exception.TokenDoesntExistException;
import com.example.securityservice.service.AuthService;
import com.example.securityservice.service.JwtService;
import com.example.securityservice.service.RefreshTokenService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth-api")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public String addUser(@RequestBody UserCredentials userCredentials) {

        return authService.addUser(userCredentials);
    }

    @PostMapping("/token")
    public JwtResponse getToken(@RequestBody UserCredentials userCredentials) {

        RefreshToken refreshToken=refreshTokenService.createRefreshToken(userCredentials.getUsername());
        return JwtResponse.builder().accessToken(authService.generateToken(userCredentials))
                .token(refreshToken.getToken()).build();

    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestParam String token) {


        return authService.validateToken(token);
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

      return   refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration).
                map(RefreshToken::getUser).
                map(userCredentials -> {

                    String accessToken= jwtService.generateToken(userCredentials);
                    return JwtResponse.builder().accessToken(accessToken)
                            .token(refreshTokenRequest.getToken()).build();
                }).orElseThrow(()-> new TokenDoesntExistException("Not exist"));
    }


    @GetMapping("/findByUsername/{username}")
    public UserResponse findUserByUsername(@PathVariable String username){

        return authService.findUserByUsername(username);
    }

}
