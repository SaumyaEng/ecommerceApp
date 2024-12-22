package com.ecommerce.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static final String SECRET_KEY = "l1Dqv/MsKwV3vXnG3VRDg2+bJ6Pp/EZbJHz2XLR8BBo=";
    private static final long ACCESS_TOKEN_DURATION = 15 * 60 * 1000; // 15 minutes
    private static final long REFRESH_TOKEN_DURATION = 24 * 60 * 60 * 1000; // 1 day

    // Create Access Token
    public String generateToken(String email) {
        return buildToken(email, ACCESS_TOKEN_DURATION);
    }

    // Create Refresh Token
    public String generateRefreshToken(String email) {
        return buildToken(email, REFRESH_TOKEN_DURATION);
    }

    // Validate Token
    public boolean validateToken(String token) {
        return parseClaims(token) != null;
    }

    // Extract Email from Token
    public String getEmailFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    // Internal Helper Method: Token Builder
    private String buildToken(String subject, long duration) {
        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + duration))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }

    // Internal Helper Method: Parse Token Claims
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null; // Token is invalid
        }
    }
}
