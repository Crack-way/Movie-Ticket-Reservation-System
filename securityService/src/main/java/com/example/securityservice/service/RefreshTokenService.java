package com.example.securityservice.service;


import com.example.securityservice.entity.RefreshToken;
import com.example.securityservice.exception.TokenAlreadyExpiredException;
import com.example.securityservice.repo.RefreshTokenRepo;
import com.example.securityservice.repo.UserCredentialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {


    private final RefreshTokenRepo refreshTokenRepo;

    private final UserCredentialsRepo userCredentialsRepo;


    public RefreshToken createRefreshToken(String username) {

        RefreshToken refreshToken=RefreshToken.builder().
                user(userCredentialsRepo.findByName(username).orElseThrow(() ->
                        new UsernameNotFoundException("User not found"))).
                token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(600000))
                .build();

      return  refreshTokenRepo.save(refreshToken);

    }

    public Optional<RefreshToken> findByToken(String token){

          return refreshTokenRepo.findByToken(token);

    }

    public RefreshToken verifyExpiration(RefreshToken token){

        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(token);
            throw new TokenAlreadyExpiredException("Token expired");
        }

        return token;
    }

}
