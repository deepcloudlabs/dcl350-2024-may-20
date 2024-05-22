package com.example.hr.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@ConditionalOnProperty(name = "messaging", havingValue = "kafka-websocket")
public class WebSocketConfig implements WebSocketConfigurer{
	private final WebSocketHandler webSocketHandler;
	
	public WebSocketConfig(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/hr-events")
		        .setAllowedOrigins("*");
	}

}
