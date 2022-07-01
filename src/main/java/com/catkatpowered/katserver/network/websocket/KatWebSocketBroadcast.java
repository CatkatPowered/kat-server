package com.catkatpowered.katserver.network.websocket;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.network.KatNetwork;
import com.catkatpowered.katserver.network.websocket.packet.WebSocketMessagePacket;
import com.catkatpowered.katserver.storage.KatMessageStorage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;

// extension -API-> katserver -websocket-> moseeger
// 此处监听event并转换成网络包发送给moseeger
@Slf4j
public class KatWebSocketBroadcast implements Listener {
    @EventHandler
    public void onMessageReceive(MessageReceiveEvent event) {
        KatMessageStorage.createMessage(event.getMessage());
        Gson gson = new Gson();
        /*
        判断是否为文件类型的消息包
        若是，则填充resource_token给mosseger进行下一步请求使用
        若不是，则直接发送
        */
        WebSocketMessagePacket packet;
        if (event.getMessage().isResource()) {
            KatUniMessage message = event.getMessage();
            message.setResourceToken(KatServer.KatTokenPoolAPI.newToken());
            packet = WebSocketMessagePacket.builder().message(message).build();
        } else {
            packet = WebSocketMessagePacket.builder().message(event.getMessage()).build();
        }
        try {
            for (Session session : KatNetwork.getSessions()) {
                session.getRemote().sendString(gson.toJson(packet));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
