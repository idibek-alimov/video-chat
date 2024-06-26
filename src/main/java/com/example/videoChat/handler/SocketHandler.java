package com.example.videoChat.handler;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;



public class SocketHandler extends TextWebSocketHandler{
    private static CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
    @OnOpen
    public void whenOpening(Session session) throws IOException, EncodeException {
        System.out.println("adding session");
        sessions.add(session);
    }

    @OnMessage
    public void process(String data, Session session) throws IOException {
        System.out.println("Got signal - " + data);
        /*
         * When signal is received, send it to other participants other than self. In
         * real world, signal should be sent to only participant's who belong to
         * particular video conference.
         */
        for (Session sess : sessions) {
            if (!sess.equals(session)) {
                sess.getBasicRemote().sendText(data);
            }
        }
    }

    @OnClose
    public void whenClosing(Session session) {
        System.out.println("Close!");
        sessions.remove(session);
    }
    public void sendMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
//    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Override
//    public void handleTextMessage(WebSocketSession session,TextMessage textMessage){
//        System.out.println("Received a text message"+textMessage.getPayload());
//        try{
//            for(WebSocketSession webSocketSession:sessions){
//                if(webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())){
//                    webSocketSession.sendMessage(textMessage);
//                }
//            }
//        }
//        catch(Exception e){
//            throw new RuntimeException();
//        }
//    }
//
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session){
//        sessions.add(session);
//    }

}
