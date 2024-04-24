package com.example.videoChat.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler{
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session,TextMessage textMessage){
        System.out.println("Received a text message"+textMessage.getPayload());
        try{
            for(WebSocketSession webSocketSession:sessions){
                if(webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())){
                    webSocketSession.sendMessage(textMessage);
                }
            }
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        sessions.add(session);
    }

//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        System.out.println(session.getId());
//        System.out.println(message.getPayload());
//        super.handleMessage(session, message);
//    }

}
