package com.catkatpowered.katserver;

import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.type.DatabaseType;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.RegisteredListener;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.message.KatUniMessageTypeManager;
import java.io.File;
import java.util.Map;

/**
 * Kat API 入口
 *
 * @author hanbings
 * @author suibing112233
 */
@SuppressWarnings("unused")
public class KatServer {

    // EventBus API
    public static final class KatEventBusAPI {

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

        public void registerEvent(Event event) {
            KatEventManager.registerEvent(event);
        }
    }

    // 扩展 API
    public static final class KatExtensionAPI {

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
    }


    // KatUniMessageType API
    public static final class KatUniMessageTypeAPI {

        public static boolean addMessageType(String msgType) {
            return KatUniMessageTypeManager.addMessageType(msgType);
        }
    }

    // KatConfig API
    public static final class KatConfigAPI {

        public static Map<String, Object> getConfig() {
            return KatConfigManager.getAllConfig();
        }

        public static Object getConfig(String configNode) {
            return KatConfigManager.getConfig(configNode);
        }
    }

    // KatDatabase API
    public static final class KatDatabaseAPI {

        // 取得链接器
        public static DatabaseConnector getConnector() {
            return KatDatabaseManager.getConnector();
        }

        public static DatabaseConnector getConnector(DatabaseType type) {
            return KatDatabaseManager.getConnector(type);
        }

        // 获取执行器
        public static DatabaseActions getActions() {
            return KatDatabaseManager.getActions();
        }

        public static DatabaseActions getActions(DatabaseType type) {
            return KatDatabaseManager.getActions(type);
        }
    }
}
