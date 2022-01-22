package com.catkatpowered.katserver.event;

import com.catkatpowered.katserver.event.interfaces.Listener;

/**
 * 事件总线
 *
 * @author hanbings
 */
public class KatEventManager {
    static EventBus bus;

    public static void KatEventMain() {
        bus = new EventBus();
    }

    public static void callEvent(Event event) {
        bus.callEvent(event);
    }

    public static void registerEvent(Event event) {
        bus.registerEvent(event);
    }

    public static void unregisterEvent(Event event) {
        bus.unregisterEvent(event);
    }

    public static void registerListener(Listener listener) {
        bus.registerListener(listener);
    }

    public static void unregisterListener(Listener listener) {
        bus.unregisterListener(listener);
    }

    @Deprecated
    public static EventBus getEventBus() {
        return bus;
    }

    public static RegisteredListener getEventHandler(Event event) {
        return bus.getEventHandler(event);
    }
}
