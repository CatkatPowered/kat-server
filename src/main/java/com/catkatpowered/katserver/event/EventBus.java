package com.catkatpowered.katserver.event;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.catkatpowered.katserver.event.interfaces.Blockable;
import com.catkatpowered.katserver.event.interfaces.Cancellable;
import com.catkatpowered.katserver.event.interfaces.EventHandler;
import com.catkatpowered.katserver.event.interfaces.Listener;

import lombok.Getter;

/**
 * @author hanbings
 * @author suibing112233
 */
@SuppressWarnings("unused")
public class EventBus {

    @Getter
    private final Map<Class<? extends Event>, RegisteredListener> eventHandlers = new ConcurrentHashMap<>();
    private static final EventBus instance = new EventBus();

    private EventBus() {
    }

    public static EventBus getInstance() {
        return instance;
    }

    public void callEvent(Event event) {
        if (eventHandlers.containsKey(event.getClass())) {
            for (RegisteredHandler handler : eventHandlers.get(event.getClass()).getHandlerList()) {
                if (event instanceof Blockable && ((Blockable) event).isBlocked()) {
                    return;
                }
                // isIgnoreCancelled为 true 时继续执行
                if (event instanceof Cancellable
                        && ((Cancellable) event).isCancelled()
                        && !handler.isIgnoreCancelled()) {
                    continue;
                }
                try {
                    handler.getMethod().invoke(handler.getListener(), event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public RegisteredListener getEventHandler(Event event) {
        return eventHandlers.get(event.getClass());
    }

    public void registerEvent(Event event) {
        if (!eventHandlers.containsKey(event.getClass())) {
            eventHandlers.put(event.getClass(), new RegisteredListener());
        }
    }

    public void unregisterEvent(Event event) {
        eventHandlers.remove(event.getClass());
    }

    public void registerListener(Listener listener) {
        Stream.of(listener.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class))
                .filter(method -> eventHandlers.containsKey(method.getParameterTypes()[0]))
                .forEach(method -> {
                    method.setAccessible(true);

                    var event = method.getParameterTypes()[0];
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    eventHandlers.get(event).addHandler(
                            new RegisteredHandler(
                                    annotation.priority(),
                                    annotation.ignoreCancelled(),
                                    listener,
                                    method));

                    method.setAccessible(false);
                });
    }

    public void unregisterListener(Listener listener) {
        Stream.of(listener.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class))
                .filter(method -> eventHandlers.containsKey(method.getParameterTypes()[0]))
                .forEach(method -> {
                    method.setAccessible(true);

                    var event = method.getParameterTypes()[0];
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    eventHandlers.get(event).removeHandler(listener);

                    method.setAccessible(false);
                });
    }
}
