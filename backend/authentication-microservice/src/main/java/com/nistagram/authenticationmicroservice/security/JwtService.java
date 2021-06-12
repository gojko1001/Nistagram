package com.nistagram.authenticationmicroservice.security;

import com.nistagram.authenticationmicroservice.logger.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("Nistagram")
    private String APP_NAME;

    @Value("rlwHsKbnKROj0bg")
    private String KEY;

    @Value("3600000")
    private int EXPIRATION_PERIOD;

    @Value("Authorization")
    private String AUTH_HEADER;


    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTH_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
            return authorizationHeader.substring(7);
        return null;
    }


    public String createToken(String username/*, Role role*/) {
        Logger.info("Try to create token for user.", username);
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                //.claim("role", role)
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
