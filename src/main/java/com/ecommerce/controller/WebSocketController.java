package com.ecommerce.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @MessageMapping("/product-price/{productId}")
    @SendTo("/topic/product-price/{productId}")
    public Map<String, Object> sendRealTimePrice(@DestinationVariable Long productId) {
        // Generate a random price with two decimal places
        double simulatedPrice = Math.round(Math.random() * 100 * 100.0) / 100.0;
        return Map.of("productId", productId, "realTimePrice", simulatedPrice);
    }
}
