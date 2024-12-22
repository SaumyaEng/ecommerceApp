package com.ecommerce.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private static final String DESTINATION = "/topic/prices";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 5000)
    public void broadcastPriceUpdates() {
        double randomPrice = generateRandomPrice();
        messagingTemplate.convertAndSend(DESTINATION, Map.of("price", randomPrice));
    }

    private double generateRandomPrice() {
        return Math.round((Math.random() * 100) * 100.0) / 100.0; // Rounded to 2 decimal places
    }
}
