package com.catkatpowered.katserver.event;

import com.catkatpowered.katserver.event.interfaces.Listener;

/**
 * 事件总线
 *
 * @author hanbings
 */
public class KatEventManager {

    public static void init() {
    }

    public static void callEvent(Event event) {
        EventBus.getInstance().callEvent(event);
    }

    public static void registerEvent(Event event) {
        EventBus.getInstance().registerEvent(event);
    }

    public static void unregisterEvent(Event event) {
        EventBus.getInstance().unregisterEvent(event);
    }

    public static void registerListener(Listener listener) {
        EventBus.getInstance().registerListener(listener);
    }

    public static void unregisterListener(Listener listener) {
        EventBus.getInstance().unregisterListener(listener);
    }

    @Deprecated
    public static EventBus getEventBus() {
        return EventBus.getInstance();
    }

    public static RegisteredListener getEventHandler(Event event) {
        return EventBus.getInstance().getEventHandler(event);
    }
}
