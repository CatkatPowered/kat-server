package com.catkatpowered.katserver.network;

import lombok.Builder;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashMap;
import java.util.UUID;

public class KatNetworkSession {
    final HashMap<Session, String> WebSocketSession = new HashMap<>();

    public String generateToken(Session session) {
        String token = UUID.randomUUID().toString();
        WebSocketSession.put(session, token);
        return token;
    }

    public void revokeToken(Session session) {
        WebSocketSession.remove(session);
    }

}
