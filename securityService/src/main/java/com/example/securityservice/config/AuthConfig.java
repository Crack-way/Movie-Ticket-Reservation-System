package com.example.securityservice.config;

import com.example.securityservice.repo.UserCredentialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class AuthConfig {

    private final UserCredentialsRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService() {


        return username -> userRepo.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("auth-api/register","auth-api/token","auth-api/validate","auth-api/refreshToken","auth-api/findByUsername/{username}").permitAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){


        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{


        return config.getAuthenticationManager();
    }


}
