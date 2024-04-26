package com.example.videoChat.handler;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
//
//@ServerEndpoint("/signal")
//public class NewSocketHandler {
//    private static CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
//    @OnOpen
//    public void onOpen(Session session) {
//        // Add session to the list of sessions
//        System.out.println("adding session");
//        sessions.add(session);
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) throws IOException {
//        // Handle incoming messages
//        System.out.println("Got signal - " + message);
//        /*
//         * When signal is received, send it to other participants other than self. In
//         * real world, signal should be sent to only participant's who belong to
//         * particular video conference.
//         */
//        for (Session sess : sessions) {
//            if (!sess.equals(session)) {
//                sess.getBasicRemote().sendText(message);
//            }
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        // Remove session from the list of sessions
//        System.out.println("Close!");
//        sessions.remove(session);
//    }
//}
