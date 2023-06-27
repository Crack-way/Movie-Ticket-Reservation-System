package com.example.securityservice.service;


import com.example.securityservice.dto.UserResponse;
import com.example.securityservice.entity.RefreshToken;
import com.example.securityservice.entity.UserCredentials;
import com.example.securityservice.enums.Role;
import com.example.securityservice.exception.UserDoesNotExistException;
import com.example.securityservice.repo.UserCredentialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredentialsRepo userCredentialsRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;

    private final UserDetailsService userDetailsService;

    private final RefreshTokenService refreshTokenService;

    public String addUser(UserCredentials credentials) {
        credentials.setRole(Role.USER);
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));

        userCredentialsRepo.save(credentials);
        return "Successfully User Added";

    }

    public String generateToken(UserCredentials userCredentials) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()));

        if (authentication.isAuthenticated()) {

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userCredentials.getUsername());

            return jwtService.generateToken(userCredentials);
        } else {
            throw new UserDoesNotExistException("User Not Exist");
        }
    }

    public boolean validateToken(String token) {

        String username = jwtService.extractUsername(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return jwtService.isTokenValid(token, userDetails);
    }

    public UserResponse findUserByUsername(String username) {

        UserCredentials userCredentials=userCredentialsRepo.findByName(username).orElseThrow();
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(userCredentials.getEmail());
        return userResponse;
    }
}
