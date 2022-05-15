package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.common.constants.KatPacketTypeConstants;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.network.packet.*;
import com.catkatpowered.katserver.storage.KatMessageStorage;
import com.google.gson.Gson;
import io.javalin.Javalin;
import lombok.Getter;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;

import java.util.List;
import java.util.Optional;

public class KatNetwork {

    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    private static Javalin network;

    // 包含所有moseeger客户端,实现无限客户端
    @Getter
    private static List<Session> sessions;

    private KatNetwork() {
        Javalin server = Javalin.create(config -> {
            config.server(() -> {
                Server app = new Server();
                SslContextFactory sslContextFactory = KatCertUtil.getSslContextFactory();
                ServerConnector sslConnector = new ServerConnector(app, sslContextFactory);
                sslConnector.setPort(KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_NETWORK_PORT).get());
                app.setConnectors(new Connector[]{sslConnector});
                return app;
            });
        });
        Gson gson = new Gson();
        // HTTP Handlers
        server.get("/resource", new HttpHandler());

        // WebSocket Handlers
        server.ws("/websocket", ws -> {
            ws.onConnect(wsConnectContext -> {
                // 发送服务端描述包
                wsConnectContext.send(gson.toJson(ServerDescriptionPacket.builder().build()));
                sessions.add(wsConnectContext.session);
            });
            ws.onClose(wsCloseContext -> {
                sessions.remove(wsCloseContext.session);
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
        });

        network = server.start();
    }

    public static KatNetwork getInstance() {
        return Instance;
    }
}
