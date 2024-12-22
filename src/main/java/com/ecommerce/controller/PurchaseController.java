package com.ecommerce.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    @PostMapping("/buy/{productId}")
    public ResponseEntity<Map<String, Object>> buyProduct(@PathVariable Long productId) {
        // Simulating a successful purchase response
        Map<String, Object> response = Map.of(
            "message", "Purchase successful",
            "productId", productId
        );
        return ResponseEntity.ok(response);
    }
}
