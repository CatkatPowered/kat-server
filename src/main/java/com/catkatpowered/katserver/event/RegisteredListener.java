package com.catkatpowered.katserver.event;

import java.util.ArrayList;
import java.util.List;

import com.catkatpowered.katserver.event.interfaces.Listener;

@SuppressWarnings("unused")
public class RegisteredListener {
    private final List<RegisteredHandler> handlerList = new ArrayList<>();
    private final List<Integer> priorityIndex = new ArrayList<>();

    public RegisteredListener() {
        for (int count = 0; count < 6; count++) {
            priorityIndex.add(0);
        }
    }

    public List<RegisteredHandler> getHandlerList() {
        return handlerList;
    }

    public void addHandler(RegisteredHandler handler) {
        // 这一处非常糟糕的说
        int priority = getPriorityShadow(handler.getPriority());
        handlerList.add(priorityIndex.get(priority), handler);
        for (int count = priority; count < 6; count++) {
            priorityIndex.set(count, priorityIndex.get(count) + 1);
        }
    }

    public void removeHandler(RegisteredHandler handler) {
        handlerList.removeIf(registeredHandler -> registeredHandler.getListener().equals(handler.getListener()));
    }

    public void removeHandler(Listener listener) {
        handlerList.removeIf(registeredHandler -> registeredHandler.getListener().equals(listener));
    }

    private int getPriorityShadow(EventPriority priority) {
        return switch (priority) {
            case LOWEST -> 0;
            case LOW -> 1;
            case HIGH -> 3;
            case HIGHEST -> 4;
            case MONITOR -> 5;
            default -> 2;
        };
    }
}
