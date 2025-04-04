package com.seplag.processoseletivo.infra.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long tokenValidadeTokenEmMs;
    private final long refreshTokenValidadeTokenEmMs;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validity}") long tokenValidadeTokenEmMs,
            @Value("${jwt.refresh-token-validity}") long refreshTokenValidadeTokenEmMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.tokenValidadeTokenEmMs = tokenValidadeTokenEmMs;
        this.refreshTokenValidadeTokenEmMs = refreshTokenValidadeTokenEmMs;
    }

    public String geraTokenDeAcesso(String userId, String email) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("email", email)
                .claim("email", email)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidadeTokenEmMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }





}
