package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.DatabaseType;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.sqlite.SqliteConnector;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 */
public class KatDatabaseManager {
    // 这会还得 Controller 暂存连接器
    static DatabaseConnector connector;

    @SuppressWarnings("all")
    public static void init() {
        // 加载数据库
        // 读配置文件
        DatabaseType type = DatabaseType.lookup((String) KatServer.KatConfigAPI.getConfig("database_type"));
        String url = (String) KatServer.KatConfigAPI.getConfig("database_url");
        String username = (String) KatServer.KatConfigAPI.getConfig("database_username");
        String password = (String) KatServer.KatConfigAPI.getConfig("database_password");

        switch (type) {
            case SQLite -> connector = new SqliteConnector();
        }

        connector.loadDatabase(url,username, password);
    }
}
