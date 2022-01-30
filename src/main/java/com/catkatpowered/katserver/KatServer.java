package com.catkatpowered.katserver;

import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.RegisteredListener;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.log.KatLoggerManager;
import com.catkatpowered.katserver.message.KatUniMessageTypeManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
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

    // 日志 API
    public static final class KatLoggerAPI {

        public static Logger getLogger() {
            return KatLoggerManager.getLogger();
        }

        public static Logger getLogger(String loggerName) {
            return KatLoggerManager.getLogger(loggerName);
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

        public static Map<String, Object> getAllConfig() {
            return KatConfigManager.getAllConfig();
        }

        public static Object getConfig(String configNode) {
            return KatConfigManager.getConfig(configNode);
        }
    }

    // KatDatabase API
    public static final class KatDatabaseAPI {

        // 增加一行数据
        public static void create(String table, Object data) {
            KatDatabaseManager.create(table, data);
        }

        // 删除一行数据
        public static void delete(String table, Object data) {
            KatDatabaseManager.delete(table, data);
        }

        // 查询一组数据
        public static <T> List<T> read(String table, T data) {
            return KatDatabaseManager.read(table, data);
        }

        // 更新一行数据
        public static void update(String table, Object data) {
            KatDatabaseManager.update(table, data);
        }
    }
}
