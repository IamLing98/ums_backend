package com.linkdoan.backend.config;

import com.linkdoan.backend.handler.CustomHandshakeHandler;
import com.linkdoan.backend.handler.CustomHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("/*")
                .setAllowedOrigins("*")
                .setAllowedOrigins("http://localhost:3000","http://localhost:3001")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .addInterceptors(new CustomHandshakeInterceptor(jwtTokenUtil)).withSockJS();
//				.setAllowedOrigins("http://localhost:3001")
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/", "/queue/");
        config.setApplicationDestinationPrefixes("/app"); 
    }

}
