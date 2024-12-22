package com.ecommerce.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private static final String BROKER_PREFIX = "/topic";
    private static final String APP_PREFIX = "/app";
    private static final String ENDPOINT = "/ws";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(BROKER_PREFIX); // Prefix for topics where clients subscribe
        config.setApplicationDestinationPrefixes(APP_PREFIX); // Prefix for messages sent to application controllers
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ENDPOINT)
                .setAllowedOrigins("http://localhost:3000", "http://example.com") // Restrict to trusted origins
                .withSockJS(); // Fallback for browsers not supporting WebSocket
    }
}
