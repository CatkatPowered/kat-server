package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.network.packet.*;
import com.catkatpowered.katserver.storage.KatMessageStorage;
import com.google.gson.Gson;
import io.javalin.websocket.WsConfig;

import java.util.List;
import java.util.Optional;

public class KatWebSocketIncome {

    public static void KatNetworkIncomeHandler(WsConfig ws) {
        Gson gson = new Gson();
        ws.onConnect(wsConnectContext -> {
            // 发送服务端描述包
            wsConnectContext.send(gson.toJson(ServerDescriptionPacket.builder().build()));
            KatNetwork.getSessions().add(wsConnectContext.session);
        });
        ws.onClose(wsCloseContext -> {
            KatNetwork.getSessions().remove(wsCloseContext.session);
        });
        ws.onMessage(wsMessageContext -> {
            String type = gson.fromJson(wsMessageContext.message(),
                    BasePacket.class).getType();
            switch (type) {
                case KatPacketTypeConstants.MESSAGE_PACKET:
                    WebSocketMessagePacket webSocketMessagePacket = gson.fromJson(wsMessageContext.message(),
                            WebSocketMessagePacket.class);
                    // 异步保存消息到数据库
                    KatMessageStorage.createMessage(webSocketMessagePacket.getMessage());
                    // 处理消息发送事件
                    KatServer.KatEventBusAPI.callEvent(new MessageSendEvent(webSocketMessagePacket.getMessage().extensionID, webSocketMessagePacket.getMessage()));
                    return;
                case KatPacketTypeConstants.MESSAGE_QUERY_PACKET:
                    WebsocketMessageQueryPacket websocketMessageQueryPacket = gson.fromJson(wsMessageContext.message(),
                            WebsocketMessageQueryPacket.class);
                    // 处理消息查询并返回
                    Optional<List<KatUniMessage>> messages = KatMessageStorage.searchMessage(websocketMessageQueryPacket.getExtensionId(), websocketMessageQueryPacket.getMessageGroup(), websocketMessageQueryPacket.getStartTimeStamp(), websocketMessageQueryPacket.getEndTimeStamp());
                    websocketMessageQueryPacket.setMessages(messages.get());
                    wsMessageContext.send(websocketMessageQueryPacket);
                    return;
            }
            wsMessageContext.send(gson.toJson(ErrorPacket.builder().error("Unknown Packet").build()));
        });
    }
}
