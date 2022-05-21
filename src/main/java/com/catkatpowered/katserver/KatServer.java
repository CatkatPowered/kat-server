package com.catkatpowered.katserver;

import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.RegisteredListener;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.message.KatUniMessageTypeManager;
import com.catkatpowered.katserver.task.KatTaskManager;
import com.catkatpowered.katserver.tokenpool.KatTokenPoolManager;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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

        public static void registerEvent(Event event) {
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

        public static <T> Optional<T> getConfig(String configNode) {
            return KatConfigManager.getConfig(configNode);
        }
    }

    // KatDatabase API

    /**
     * 封装对数据库的操作
     */
    public static final class KatDatabaseAPI {
        /**
         * 注册自定义<em>connector</em>
         *
         * @param connector
         * @see DatabaseConnector
         */
        public static void register(DatabaseConnector connector) {
            KatDatabaseManager.register(connector);
        }

        /**
         * 在集合中创建新的对象
         *
         * @param collection
         * @param data
         */
        public static void create(String collection, Object... data) {
            for (Object object : data) {
                KatDatabaseManager.create(collection, object);
            }
        }

        /**
         * 在集合中创建新的对象
         *
         * @param collection
         * @param data
         */
        public static void create(String collection, List<Object> data) {
            KatDatabaseManager.create(collection, data);
        }

        /**
         * 更新数据库中的对象
         *
         * @param collection
         * @param index
         * @param data
         */
        public static void update(String collection, Map<String, Object> index, Object data) {
            KatDatabaseManager.update(collection, index, data);
        }

        /**
         * 删除数据库中的对象
         *
         * @param collection
         * @param index
         */
        public static void delete(String collection, Map<String, Object> index) {
            KatDatabaseManager.delete(collection, index);
        }

        /**
         * 读取数据库中的对象
         *
         * @param <T>
         * @param collection
         * @param index
         * @param type
         * @return
         */
        public static <T> List<T> read(String collection, Map<String, Object> index, Class<T> type) {
            return KatDatabaseManager.read(collection, index, type);
        }

        /**
         * 查询数据库中的对象
         */
        public static <T, V> List<T> search(String collection, String data, V top, V bottom, int limit, Class<T> type) {
            return KatDatabaseManager.search(collection, data, top, bottom, limit, type);
        }
    }

    public static final class KatTaskAPI {

        public static Future<?> addTask(Runnable task) {
            return KatTaskManager.addTask(task);
        }

        public static <T> Future<T> addTask(Callable<T> task) {
            return KatTaskManager.addTask(task);
        }

        public static <T> Future<T> addTask(Runnable task, T result) {
            return KatTaskManager.addTask(task, result);
        }

        public static void exec(Runnable task) {
            KatTaskManager.exec(task);
        }

    }

    public static final class KatTokenPoolAPI {
        /**
         * 生成新的<em>token</em>
         *
         * @return
         */
        public static String newToken() {
            return KatTokenPoolManager.newToken();
        }

        /**
         * 撤销<em>token</em>
         *
         * @param tokeString
         * @return 是否成功撤销
         */
        public static boolean revokeToken(String tokeString) {
            return KatTokenPoolManager.revokeToken(tokeString);
        }

        /**
         * 检查<em>token</em>是否过期
         *
         * @param token
         * @return
         */
        public static boolean checkToken(String token) {
            return KatTokenPoolManager.checkToken(token);
        }
    }
}
