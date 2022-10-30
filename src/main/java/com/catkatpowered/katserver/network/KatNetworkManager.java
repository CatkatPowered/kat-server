package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.network.websocket.KatWebSocketBroadcast;
import io.javalin.Javalin;

public class KatNetworkManager {

  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static void init() {
    KatNetwork.getInstance();
    KatEventManager.registerListener(new KatWebSocketBroadcast());
  }

  public static Javalin getNetworkServer() {
    return KatNetwork.getNetwork();
  }
}
