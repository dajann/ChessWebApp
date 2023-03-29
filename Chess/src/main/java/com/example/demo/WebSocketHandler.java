package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	private Game game = new Game();
	
	private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		if(game.chessBoard.player1.getId() == null) {
			game.chessBoard.player1.setId(session.getId());
			webSocketSessions.add(session);
			}
		else if(game.chessBoard.player2.getId() == null) {
			game.chessBoard.player2.setId(session.getId());
			webSocketSessions.add(session);
			}
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> messageMap = mapper.readValue(message.getPayload(), new TypeReference<Map<String,Object>>(){});
        String messageType = (String)messageMap.get("type");
	    
        if (messageType.equals("chat")) {
        	 String text = (String) messageMap.get("text");
        	 for(WebSocketSession webSocketSession : webSocketSessions)
 				synchronized(webSocketSession) {
 					if(session.getId() != webSocketSession.getId()) 
 						webSocketSession.sendMessage(new TextMessage(text));
 				}
        }
        else if (messageType.equals("end")) {
			for(WebSocketSession webSocketSession : webSocketSessions)
				synchronized(webSocketSession) {
				webSocketSession.sendMessage(new TextMessage(game.chessBoard.end()));
				}
	    } else if (messageType.equals("restart")) {
			String jsonBoard = mapper.writeValueAsString(game.chessBoard.restart());
			for(WebSocketSession webSocketSession : webSocketSessions)
				synchronized(webSocketSession) {
				webSocketSession.sendMessage(new TextMessage(jsonBoard));
				}
	    } else if(messageType.equals("positions") && session.getId() == game.chessBoard.getCurrentPlayer().getId()) {
	    	 Map<String, Integer> positions = (Map<String, Integer>) messageMap.get("positions");
	    	  int position1 = positions.get("x");
	    	  int position2 = positions.get("y");
			String jsonBoard = mapper.writeValueAsString(game.chessBoard.update(position1, position2));
			for(WebSocketSession webSocketSession : webSocketSessions)
				synchronized(webSocketSession) {
				webSocketSession.sendMessage(new TextMessage(jsonBoard));
				}
			}
	}
	@Override 
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
			throws Exception {
		if(game.chessBoard.player1.getId() == session.getId())
			game.chessBoard.player1.setId(null);
		else game.chessBoard.player2.setId(null);
		webSocketSessions.remove(session);
	}
	

}