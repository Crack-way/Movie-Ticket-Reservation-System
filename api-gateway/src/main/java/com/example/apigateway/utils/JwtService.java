package com.example.apigateway.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;



@Service
public class JwtService {


    private static final String SECRET_KEY = "597133743677397A24432646294A404E635266546A576E5A7234753778214125";



    public void isTokenValid(String token){

       Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
    }




    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }


}
