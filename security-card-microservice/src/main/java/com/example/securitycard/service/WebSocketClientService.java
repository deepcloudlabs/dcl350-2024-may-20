package com.example.securitycard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class WebSocketClientService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	
	
	public WebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connectToWebSocketServer() {
		webSocketClient.execute(this, "ws://localhost:4100/hr/api/v1/hr-events");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Created a new websocket session [%s].".formatted(session.getId()));		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New message from websocket server [%s] has arrived: %s".formatted(session.getId(),
				message.getPayload().toString()));		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occured at the session [%s]: %s".formatted(session.getId(), e.getMessage()));		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Session [%s] is closed.".formatted(session.getId()));		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
