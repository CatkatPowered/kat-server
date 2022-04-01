package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.network.packet.WebSocketMessagePacket;
import com.catkatpowered.katserver.storage.KatMessageStorage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KatNetworkHandler implements Listener {
    @EventHandler
    public void onMessageReceive(MessageReceiveEvent event) {
        KatMessageStorage.createMessage(event.getMessage());
        Gson gson = new Gson();
        WebSocketMessagePacket packet = WebSocketMessagePacket.builder().extensionId(event.getExtensionId()).message(event.getMessage()).build();
        KatNetwork.getSessions().forEach(session -> {
            try {
                session.getRemote().sendString(gson.toJson(packet));
            } catch (Exception e) {
                log.error(e.getStackTrace().toString());
            }
        });
    }
}
