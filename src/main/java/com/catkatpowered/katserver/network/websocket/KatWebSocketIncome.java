package com.catkatpowered.katserver.network.websocket;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatMiscConstants;
import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.network.KatNetwork;
import com.catkatpowered.katserver.network.websocket.packet.*;
import com.catkatpowered.katserver.storage.KatMessageStorage;
import com.google.gson.Gson;
import io.javalin.websocket.WsConfig;
import org.eclipse.jetty.websocket.api.Session;

import java.util.List;
import java.util.Optional;

public class KatWebSocketIncome {

    public static void KatNetworkIncomeHandler(WsConfig ws) {
        Gson gson = new Gson();
        ws.onConnect(wsConnectContext -> {
            // 发送服务端描述包
            wsConnectContext.send(gson.toJson(ServerDescriptionPacket.builder().version(KatMiscConstants.KAT_SERVER_VERSION).build()));
            List<Session> sessions = KatNetwork.getSessions();
            sessions.add(wsConnectContext.session);
            KatNetwork.setSessions(sessions);
        });
        ws.onClose(wsCloseContext -> KatNetwork.getSessions().remove(wsCloseContext.session));
        ws.onMessage(wsMessageContext -> {
            String type = gson.fromJson(wsMessageContext.message(),
                    BasePacket.class).getType();
            switch (type) {
                case KatPacketTypeConstants.MESSAGE_PACKET -> {
                    WebSocketMessagePacket webSocketMessagePacket = gson.fromJson(wsMessageContext.message(),
                            WebSocketMessagePacket.class);
                    if (webSocketMessagePacket.getMessage().isResource() && !KatServer.KatTokenPoolAPI.checkToken(webSocketMessagePacket.getMessage().resourceToken)) {
                        wsMessageContext.send(gson.toJson(ErrorPacket.builder().error("Invalid Resource Token").build()));
                        return;
                    }
                    // 异步保存消息到数据库
                    KatMessageStorage.createMessage(webSocketMessagePacket.getMessage());
                    // 处理消息发送事件
                    KatServer.KatEventBusAPI.callEvent(MessageSendEvent.builder().extensionId(webSocketMessagePacket.getMessage().extensionID).message(webSocketMessagePacket.getMessage()).build());

                    return;
                }
                case KatPacketTypeConstants.MESSAGE_QUERY_PACKET -> {
                    WebsocketMessageQueryPacket websocketMessageQueryPacket = gson.fromJson(wsMessageContext.message(),
                            WebsocketMessageQueryPacket.class);
                    // 处理消息查询并返回
                    Optional<List<KatUniMessage>> messages = KatMessageStorage.searchMessage(websocketMessageQueryPacket.getExtensionId(), websocketMessageQueryPacket.getMessageGroup(), websocketMessageQueryPacket.getStartTimeStamp(), websocketMessageQueryPacket.getEndTimeStamp());
                    websocketMessageQueryPacket.setMessages(messages.orElse(null));
                    wsMessageContext.send(websocketMessageQueryPacket);
                    return;
                }
            }
            wsMessageContext.send(gson.toJson(ErrorPacket.builder().error("Unknown Packet").build()));
        });
    }
}
