package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.network.packet.ServerDescriptionPacket;
import com.catkatpowered.katserver.network.packet.WebSocketMessagePacket;
import com.google.gson.Gson;
import io.javalin.Javalin;
import lombok.Getter;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;

import java.security.KeyStore;
import java.util.List;

public class KatNetwork {

    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    private static Javalin network;

    @Getter
    private static List<Session> sessions;

    private KatNetwork() {
        Javalin server = Javalin.create(config -> {
            config.server(() -> {
                Server app = new Server();
                SslContextFactory sslContextFactory = new SslContextFactory.Server();
                KeyStore keyStore = KatCertUtil.getKeyStore();
                sslContextFactory.setKeyStore(keyStore);
                sslContextFactory.setKeyStorePassword("catmoe");

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
                // TODO: tokenpool实现
                // katNetworkSession.revokeToken(wsCloseContext.session);
            });
            ws.onMessage(wsMessageContext -> {
                WebSocketMessagePacket webSocketMessagePacket = gson.fromJson(wsMessageContext.message(),
                        WebSocketMessagePacket.class);

                // 处理消息发送事件
                KatServer.KatEventBusAPI.callEvent(new MessageSendEvent(webSocketMessagePacket.getExtensionId(), webSocketMessagePacket.getMessage()));
                // TODO: 异步保存消息到数据库
            });
        });

        network = server.start();
    }

    public static KatNetwork getInstance() {
        return Instance;
    }
}
