package com.catkatpowered.katserver.event;

import java.util.Map;

import com.catkatpowered.katserver.event.events.MessageReceiveEvent;
import com.catkatpowered.katserver.event.events.MessageSendEvent;
import com.catkatpowered.katserver.event.interfaces.Listener;

/**
 * 事件总线
 *
 * @author hanbings
 * @author SUIBING112233
 */
public class KatEventManager {

    public static void init() {
        // TODO: 注册所有events包下的event
        // 扫包大师
        registerEvent(MessageReceiveEvent.builder().build());
        registerEvent(MessageSendEvent.builder().build());
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

    /**
     * 非常不建议直接获取 <em>EventBus</em> 的实例。
     * 
     * 若您想尽可能的使用底层，您应当使用其他已经抽象出来的 <b>{@link KatEventManager}</b> 方法。
     * 
     * @return
     */
    @Deprecated
    public static EventBus getEventBus() {
        return EventBus.getInstance();
    }

    public static RegisteredListener getEventHandler(Event event) {
        return EventBus.getInstance().getEventHandler(event);
    }

    public static Map<Class<? extends Event>, RegisteredListener> getEventHandlers() {
        return EventBus.getInstance().getEventHandlers();
    }

}
