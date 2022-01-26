package com.catkatpowered.katserver.database;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 */
public class KatDatabaseManager {

    public static void init() {
        // 加载数据库
        KatDatabaseConnector.loadDatabase();
    }
}
