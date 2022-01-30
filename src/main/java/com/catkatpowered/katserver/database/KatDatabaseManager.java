package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.interfaces.DatabaseType;
import com.catkatpowered.katserver.database.sqlite.SqliteActions;
import com.catkatpowered.katserver.database.sqlite.SqliteConnector;

import java.util.List;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 */
public class KatDatabaseManager {
    // 这会还得 Controller 暂存连接器
    static DatabaseConnector connector;
    static DatabaseActions actions;

    @SuppressWarnings("all")
    public static void init() {
        // 加载数据库
        // 读配置文件
        DatabaseType type = DatabaseType.lookup((String) KatServer.KatConfigAPI.getConfig("database_type"));
        String url = (String) KatServer.KatConfigAPI.getConfig("database_url");
        String username = (String) KatServer.KatConfigAPI.getConfig("database_username");
        String password = (String) KatServer.KatConfigAPI.getConfig("database_password");

        switch (type) {
            case SQLite -> {
                connector = new SqliteConnector();
                actions = new SqliteActions();
            }
        }

        connector.loadDatabase(url,username, password);
    }

    // 增加一行数据
    public static void create(String table, Object data) {
        actions.create(connector.getConnection(), table, data);
    }
    // 删除一行数据
    public static void delete(String table, Object data) {
        actions.delete(connector.getConnection(), table, data);
    }
    // 查询一组数据
    public static <T> List<T> read(String table, T data) {
        return actions.read(connector.getConnection(), table, data);
    }
    // 更新一行数据
    public static void update(String table, Object data) {
        actions.update(connector.getConnection(), table, data);
    }
}
