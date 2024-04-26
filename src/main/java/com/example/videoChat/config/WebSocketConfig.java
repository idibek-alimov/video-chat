package com.example.videoChat.config;

//import com.example.videoChat.handler.NewSocketHandler;
//import com.example.videoChat.handler.SocketHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import com.example.videoChat.handler.SocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import java.io.IOException;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    @Bean
//    public NewSocketHandler videoChatHandler() {
//        return new NewSocketHandler();
//    }
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler((WebSocketHandler) videoChatHandler(), "/signal").setAllowedOrigins("*");
//    }
//}
import jakarta.websocket.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/signal");
    }

    public static class MyWebSocketHandler extends TextWebSocketHandler {
        private static CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            // Connection opened
            System.out.println("WebSocket connection opened");
            sessions.add(session);
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            // Message received
            System.out.println("Received message: " + message.getPayload());
            for(WebSocketSession sess:sessions){
                if(!session.equals(sess)){
                    sess.sendMessage(message);
                }
            }
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            // Connection closed
            System.out.println("WebSocket connection closed");
            sessions.remove(session);
        }
    }
}