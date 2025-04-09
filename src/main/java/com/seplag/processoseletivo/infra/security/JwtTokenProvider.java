package com.seplag.processoseletivo.infra.security;

import com.seplag.processoseletivo.domain.enums.Role;
import com.seplag.processoseletivo.domain.model.Usuario;
import io.jsonwebtoken.Claims;
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

    public String geraTokenDeAcesso(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("email", usuario.getEmail())
                .claim("roles", usuario.getRoles().stream()
                        .map(Role::name)
                        .toList())
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidadeTokenEmMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String geraRefreshToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidadeTokenEmMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validaToken(String token, String tipo) {
        try {
            Claims claims = getClaims(token);
            String type = claims.get("type", String.class);
            return type.equals(tipo) && !isTokenExpirado(claims);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsuarioIdfromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpirado(Claims claims) {
        return claims.getExpiration().before(new Date());
    }





}
