package com.catkatpowered.katserver;

import com.catkatpowered.katserver.api.KatLoggerManager;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.RegisteredListener;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Kat API 入口
 */
@SuppressWarnings("unused")
public class KatServer {
    // EventBus API
    public void registerEvent(Event event) {
        KatEventManager.registerEvent(event);
    }

    public static void unregisterEvent(Event event) {
        KatEventManager.unregisterEvent(event);
    }

    public static void registerListener(Listener listener) {
        KatEventManager.registerListener(listener);
    }

    public static void unregisterListener(Listener listener) {
        KatEventManager.unregisterListener(listener);
    }

    public static void callEvent(Event event) {
        KatEventManager.callEvent(event);
    }

    public static RegisteredListener getEventHandler(Event event) {
        return KatEventManager.getEventHandler(event);
    }

    // 扩展 API
    public static void loadExtensions() {
        KatExtensionManager.loadExtensions();
    }

    public static void loadExtension(File jar) {
        KatExtensionManager.loadExtension(jar);
    }

    public static void unloadExtensions() {
        KatExtensionManager.unloadExtensions();
    }

    public static void unloadExtension(String extension) {
        KatExtensionManager.unloadExtension(extension);
    }

    public static void unloadExtension(KatExtension extension) {
        KatExtensionManager.unloadExtension(extension);
    }

    // 日志 API
    public static Logger getLogger() {
        return KatLoggerManager.getLogger();
    }

    public static Logger getLogger(String name) {
        return KatLoggerManager.getLogger(name);
    }

}
