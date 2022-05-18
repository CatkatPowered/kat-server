package com.catkatpowered.katserver.network;

import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.message.KatUniMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestKatNetwork {
    @Test
    public void connection() {}

    @Test
    public void newMessageBroadcast() {
        KatEventManager.callEvent(new MessageReceiveEvent(new KatUniMessage(
                "testExtension",
                "PlainMessage",
                "101010101",
                "uuid",
                "欸嘿~",
                1652881882L,
                new ArrayList<KatUniMessage>(),
                new ArrayList<String>(),
                "",
                "",
                ""
        )));
    }
}
