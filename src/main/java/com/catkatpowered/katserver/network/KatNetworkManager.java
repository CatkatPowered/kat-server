package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.network.websocket.KatWebSocketBroadcast;

public class KatNetworkManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        KatNetwork.getInstance();
        KatEventManager.registerListener(new KatWebSocketBroadcast());
    }
}
