package com.catkatpowered.katserver.database;

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

    public static void create(String database, String collection, Object data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .create(database, collection, data);
    }

    void update(String database, String collection, Map<String, Object> index, Object data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .update(database, collection, index, data);
    }

    void delete(String database, String collection, Map<String, Object> index) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .delete(database, collection, index);
    }

    <T> List<T> read(String database, String collection, Map<String, Object> index, Class<T> type) {
        return KatDatabase.getInstance()
                .connector
                .connection()
                .read(database, collection, index, type);
    }

    public void createMany(String database, String collection, List<Object> data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .createMany(database, collection, data);
    }

    void updateOne(String database, String collection, Map<String, Object> index, Object data) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .updateOne(database, collection, index, data);
    }

    void deleteOne(String database, String collection, Map<String, Object> index) {
        KatDatabase.getInstance()
                .connector
                .connection()
                .deleteOne(database, collection, index);
    }
}
