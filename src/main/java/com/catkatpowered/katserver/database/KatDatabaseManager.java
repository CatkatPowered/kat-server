package com.catkatpowered.katserver.database;

import java.util.List;
import java.util.Map;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.mongodb.MongodbConnector;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 * @author suibing112233
 */
public class KatDatabaseManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        KatDatabase.getInstance();
        
        // 丑炸了 XD
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        String url = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_DATABASE_URL).get();
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        String username = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_DATABASE_USER_NAME).get();
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        String password = KatServer.KatConfigAPI
                .<String>getConfig(KatConfigNodeConstants.KAT_CONFIG_DATABASE_PASSWORD).get();

        // 初始化数据库连接器
        KatDatabaseManager.register(new MongodbConnector(url, username, password));
    }

    public static void register(DatabaseConnector connector) {
        KatDatabase.getInstance().register(connector);
    }

    public static void create(String collection, Object data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .create(collection, data);
    }

    public static void update(String collection, Map<String, Object> index, Object data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .update(collection, index, data);
    }

    public static void delete(String collection, Map<String, Object> index) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .delete(collection, index);
    }

    public static <T> List<T> read(String collection, Map<String, Object> index, Class<T> type) {
        return KatDatabase.getInstance()
                .connector
                .connection()
                .read(collection, index, type);
    }

    public static void create(String collection, List<Object> data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .create(collection, data);
    }
}
