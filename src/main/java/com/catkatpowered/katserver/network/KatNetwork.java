package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.config.KatConfig;
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
        // HTTP Handlers
        server.get("/", new HTTPHandler());

        // WebSocket Handlers
        server.ws("/websocket", ws -> {
            ws.onConnect(wsConnectContext -> {
            });
            ws.onClose(wsCloseContext -> {
            });
            ws.onMessage(wsMessageContext -> {
            });
        });

        network = server.start(KatConfig.getInstance().getKatNetworkPort());
    }


    public static KatNetwork getInstance() {
        return Instance;
    }
}
