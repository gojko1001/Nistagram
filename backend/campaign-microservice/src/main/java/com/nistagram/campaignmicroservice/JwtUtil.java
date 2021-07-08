package com.nistagram.campaignmicroservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("XtyV79iOKFv0GV8")
    private String API_KEY;


    public String extractUsername(String tokenHeader) {
        return extractClaim(tokenHeader, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(API_KEY).parseClaimsJws(token).getBody();
    }
}
