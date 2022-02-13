package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.interfaces.DatabaseType;
import com.catkatpowered.katserver.database.sqlite.SqliteActions;
import com.catkatpowered.katserver.database.sqlite.SqliteConnector;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 */
public class KatDatabaseManager {
    // 这会还得 Controller 暂存连接器
    static DatabaseConnector connector;
    static DatabaseActions actions;

    public static void init() {
        // 加载数据库
        // 读配置文件
        DatabaseType type = DatabaseType.lookup((String) KatServer.KatConfigAPI.getConfig("database_type"));
        String url = (String) KatServer.KatConfigAPI.getConfig("database_url");
        String username = (String) KatServer.KatConfigAPI.getConfig("database_username");
        String password = (String) KatServer.KatConfigAPI.getConfig("database_password");

        connector = pickConnector(type);
        actions = pickActions(type);

        connector.loadDatabase(url,username, password);
    }

    // 取得链接器
    public static DatabaseConnector getConnector() {
        return connector;
    }

    public static DatabaseConnector getConnector(DatabaseType type) {
        return pickConnector(type);
    }

    // 获取执行器
    public static DatabaseActions getActions() {
        return actions;
    }

    public static DatabaseActions getActions(DatabaseType type) {
        return pickActions(type);
    }

    /**
     * 根据数据库类型返回实例
     */
    private static DatabaseConnector pickConnector(DatabaseType type) {
        switch (type) {
            case MySQL, PostgreSQL, MongoDB -> {
            }
            case SQLite -> {
                return new SqliteConnector();
            }
        }
        return null;
    }

    private static DatabaseActions pickActions(DatabaseType type) {
        switch (type) {
            case MySQL, PostgreSQL, MongoDB -> {
            }
            case SQLite -> {
                return new SqliteActions();
            }
        }
        return null;
    }
}
