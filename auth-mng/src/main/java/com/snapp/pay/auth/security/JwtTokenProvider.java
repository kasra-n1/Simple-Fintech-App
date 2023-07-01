package com.snapp.pay.auth.security;

import com.snapp.pay.auth.exception.UserException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.jwtTokenValidity}")
    public long jwtTokenValidity;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {

        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String username = authentication.getName();

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer(issuer)
                .setExpiration(new Date(System.currentTimeMillis() + (jwtTokenValidity * 1000)))
                .signWith(key())
                .compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean validateJwtToken(String authToken) {

        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new UserException(HttpStatus.BAD_REQUEST, "Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw new UserException(HttpStatus.BAD_REQUEST, "JWT token is expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is not supported: {}", e.getMessage());
            throw new UserException(HttpStatus.BAD_REQUEST, "JWT token is not supported", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw new UserException(HttpStatus.BAD_REQUEST, "JWT claims string is empty", e);
        }
    }

    // get username from Jwt token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

}
