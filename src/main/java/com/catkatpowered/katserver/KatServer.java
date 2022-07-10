package com.catkatpowered.katserver;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.event.Event;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.event.RegisteredListener;
import com.catkatpowered.katserver.event.interfaces.Listener;
import com.catkatpowered.katserver.extension.KatExtension;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.message.KatUniMessage;
import com.catkatpowered.katserver.message.KatUniMessageTypeManager;
import com.catkatpowered.katserver.storage.KatStorageManager;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;
import com.catkatpowered.katserver.task.KatTaskManager;
import com.catkatpowered.katserver.tokenpool.KatTokenPoolManager;

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

        /**
         * 注册事件监听器
         * 
         * @param listener
         */
        public static void registerListener(Listener listener) {
            KatEventManager.registerListener(listener);
        }

        /**
         * 注销事件监听器
         * 
         * @param listener
         */
        public static void unregisterListener(Listener listener) {
            KatEventManager.unregisterListener(listener);
        }

        /**
         * 调用事件，当事件发生时需要调用此方法
         * 
         * @param event
         */
        public static void callEvent(Event event) {
            KatEventManager.callEvent(event);
        }

        /**
         * 获取事件句柄。所有已经注册的事件
         * 
         * @param event
         * @return
         */
        public static RegisteredListener getEventHandler(Event event) {
            return KatEventManager.getEventHandler(event);
        }

        /**
         * 注册事件
         * 
         * @param event
         */
        public static void registerEvent(Event event) {
            KatEventManager.registerEvent(event);
        }

        /**
         * 注销事件
         * 
         * @param event
         */
        public static void unregisterEvent(Event event) {
            KatEventManager.unregisterEvent(event);
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

        /**
         * 通过节点表达方式获取配置文件内容，参照 <b>{@link KatConfigNodeConstants}</b>
         * 
         * @param <T>        配置内容的具体类型
         * @param configNode 配置文件的节点表示方法
         * @return 由 <b>{@link Optional}</b> 包装的配置内容
         */
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
         * 注册自定义 {@link DatabaseConnector}
         *
         * @param connector 准备注册的DatabaseConnector
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

    /**
     * 封装对任务的操作
     */
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

    /**
     * 封装对TokenPool的操作
     */
    public static final class KatTokenPoolAPI {
        /**
         * 生成新的 <em>token</em>
         *
         * @return 返回 <em>token</em> 内容
         */
        public static String newToken() {
            return KatTokenPoolManager.newToken();
        }

        /**
         * 撤销<em>token</em>
         *
         * @param tokeString
         * @return 撤销状态
         */
        public static boolean revokeToken(String tokeString) {
            return KatTokenPoolManager.revokeToken(tokeString);
        }

        /**
         * 检查<em>token</em>是否过期
         *
         * @param token
         * @return 过期状态
         */
        public static boolean checkToken(String token) {
            return KatTokenPoolManager.checkToken(token);
        }
    }

    public static final class KatStorageAPI {

        public static KatStorageProvider getStorageProvider() {
            return KatStorageManager.getStorageProvider();
        }

        /**
         * 拉取资源位置。
         * 
         * 注意，该函数返回值中为URI，需要做适当的处理
         * 
         * @param hashString
         * @return
         */
        public static Optional<InputStream> fetch(String hashString) {
            return KatStorageManager.fetch(hashString);
        }

        /**
         * 校验文件是否正确
         * 
         * @param hashString
         * @return
         */
        public static boolean validate(String hashString) {
            return KatStorageManager.validate(hashString);
        }

        /**
         * 上传文件
         * 
         * @param resource 包含信息的容器
         * @return
         */
        public static void upload(String fileHash, InputStream inputStream) {
            KatStorageManager.upload(fileHash, inputStream);
        }

        /**
         * 删除文件。
         * 
         * @param hashString 文件hash值
         * @return
         */
        public static boolean delete(String hashString) {
            return KatStorageManager.delete(hashString);
        }

    }
}
