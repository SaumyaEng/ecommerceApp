package com.ecommerce.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entity.User;
import com.ecommerce.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        if (!credentials.containsKey("email") || !credentials.containsKey("password")) {
            throw new IllegalArgumentException("Email and password are required");
        }
        return ResponseEntity.ok(authService.login(credentials.get("email"), credentials.get("password")));
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody Map<String, String> tokenPayload) {
        if (!tokenPayload.containsKey("refresh_token")) {
            throw new IllegalArgumentException("Refresh token is required");
        }
        return ResponseEntity.ok(authService.refreshToken(tokenPayload.get("refresh_token")));
    }
}
