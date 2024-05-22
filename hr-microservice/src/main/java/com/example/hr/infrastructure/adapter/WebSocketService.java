package com.example.hr.infrastructure.adapter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.domain.event.HrBaseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "messaging", havingValue = "kafka-websocket")
public class WebSocketService implements WebSocketHandler {
	private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;

	public WebSocketService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void listenHrEvent(HrBaseEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			sessions.forEach((sessionId,session)->{
				System.err.println("Publishing event [%s] to websocket clients [%s].".formatted(eventAsJson,sessionId));
				try {
					session.sendMessage(new TextMessage(eventAsJson));
				} catch (IOException e) {
					System.err.println("Error while sending message to web socket client [%s]: %s".formatted(sessionId,e.getMessage()));
				}	
			});
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		System.err.println("New websocket session [%s] is open.".formatted(sessionId));
		sessions.put(sessionId, session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New message from websocket client [%s] has arrived: %s".formatted(session.getId(),
				message.getPayload().toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occured at the session [%s]: %s".formatted(session.getId(), e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Session [%s] is closed.".formatted(session.getId()));
		sessions.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
