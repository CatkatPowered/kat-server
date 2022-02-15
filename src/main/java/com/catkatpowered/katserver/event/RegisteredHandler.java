package com.catkatpowered.katserver.event;

import com.catkatpowered.katserver.event.interfaces.Listener;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class RegisteredHandler {
    private EventPriority priority;
    private boolean ignoreCancelled;
    private Listener listener;
    private Method method;

    private RegisteredHandler() {
    }

    public RegisteredHandler(EventPriority priority, boolean ignoreCancelled
            , Listener listener, Method method) {
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
        this.listener = listener;
        this.method = method;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }

    public Listener getListener() {
        return listener;
    }

    public Method getMethod() {
        return method;
    }
}
