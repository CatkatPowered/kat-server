package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.config.KatConfig;
import com.catkatpowered.katserver.network.packet.HttpResourceTokenPacket;
import com.catkatpowered.katserver.network.packet.ServerDescriptionPacket;
import com.catkatpowered.katserver.network.packet.WebSocketMessagePacket;
import com.google.gson.Gson;
import io.javalin.Javalin;
import lombok.Getter;
import lombok.Setter;

public class KatNetwork {

    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    @Setter
    private static Javalin network;

    private KatNetwork() {
        Javalin server = Javalin.create();
        KatNetworkSession katNetworkSession = new KatNetworkSession();
        Gson gson = new Gson();
        // HTTP Handlers
        server.get("/resource", new HttpHandler());

        // WebSocket Handlers
        server.ws("/websocket", ws -> {
            ws.onConnect(wsConnectContext -> {
                String token = katNetworkSession.generateToken(wsConnectContext.session);
                // 发送服务端描述包
                wsConnectContext.send(gson.toJson(ServerDescriptionPacket.builder().build()));
                // 发送Token包
                wsConnectContext.send(gson.toJson(HttpResourceTokenPacket.builder().token(token).build()));
            });
            ws.onClose(wsCloseContext -> {
                katNetworkSession.revokeToken(wsCloseContext.session);
            });
            ws.onMessage(wsMessageContext -> {
                WebSocketMessagePacket webSocketMessagePacket = gson.fromJson(wsMessageContext.message(), WebSocketMessagePacket.class);
                // TODO: 根据extensionId获取扩展然后发送
                // TODO: 异步保存消息到数据库
            });
        });


        network = server.start(KatConfig.getInstance().getKatNetworkPort());
    }


    public static KatNetwork getInstance() {
        return Instance;
    }
}
