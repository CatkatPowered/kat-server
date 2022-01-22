package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.common.utils.KatConfig;
import io.javalin.Javalin;
import lombok.Getter;
import lombok.Setter;

public class KatNetwork {

    private static final KatNetwork Instance = new KatNetwork();

    @Getter
    @Setter
    private static Javalin network;

    private KatNetwork() {
        Javalin katserver = Javalin.create();
        // HTTP Handlers
        //katserver.get("/http", );

        // WebSocket Handlers
        katserver.ws("/websocket", ws -> ws.onMessage(WebSocketHandler::WebSocketMessageHandler));

        network = katserver.start(KatConfig.getInstance().getKatNetworkPort());
    }


    public static KatNetwork getInstance() {
        return Instance;
    }
}
