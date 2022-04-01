package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.event.KatEventManager;

public class KatNetworkManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        KatNetwork.getInstance();
        KatEventManager.registerListener(new KatNetworkHandler());
    }
}
