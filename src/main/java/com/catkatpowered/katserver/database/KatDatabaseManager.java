package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;

import java.util.List;
import java.util.Map;

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
