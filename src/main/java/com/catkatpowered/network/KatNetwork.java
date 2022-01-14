package com.catkatpowered.network;

import io.javalin.Javalin;

public class KatNetwork {
    public static void KatNetwork() {
        Javalin katserver = Javalin.create();
        // HTTP Handlers
        //katserver.get("/http", );

        // WebSocket Handlers
        katserver.ws("/websocket", ws -> {
            ws.onMessage(ctx -> );
        })
    }
}
