package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.KatConfig;
import io.javalin.Javalin;

public class KatNetworkManager {

    public static void KatNetworkMain() {
        Javalin katserver = Javalin.create();
        // HTTP Handlers
        //katserver.get("/http", );

        // WebSocket Handlers
        katserver.ws("/websocket", ws -> ws.onMessage(WebSocketHandler::WebSocketMessageHandler));

        katserver.start(KatConfig.getInstance().getKatNetworkPort());
    }
}
