package com.agent.webshop.security;

import com.agent.webshop.domain.Role;
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

    @Value("NWebShop")
    private String APP_NAME;

    @Value("5d6Zcg2vP73nWWa")
    private String KEY;

    @Value("XtyV79iOKFv0GV8")
    private String API_KEY;

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

    public String createToken(String username, Role role) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                .claim("role", role.getName())
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public String createApiToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                .signWith(SignatureAlgorithm.HS256, API_KEY).compact();
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
