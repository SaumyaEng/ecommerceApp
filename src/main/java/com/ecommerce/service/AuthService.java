package com.ecommerce.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.security.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    public String registerUser(User user) {
        // Simulating user registration
        return "User successfully registered: " + user.getEmail();
    }

    public Map<String, String> login(String email, String password) {
        // Simulated authentication
        String accessToken = jwtUtils.generateToken(email);
        String refreshToken = jwtUtils.generateRefreshToken(email);
        
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        
        return tokens;
    }

    public String refreshToken(String refreshToken) {
        if (jwtUtils.validateToken(refreshToken)) {
            String email = jwtUtils.getEmailFromToken(refreshToken);
            return jwtUtils.generateToken(email);
        } else {
            throw new IllegalArgumentException("Invalid or expired refresh token");
        }
    }
}
