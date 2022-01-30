package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.DatabaseType;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.sqlite.SqliteConnector;

import java.sql.Connection;
import java.util.List;

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

    // 检查表是否存在
    public static boolean check(String table) { return false; }
    // 创建表
    public static void table(String table, Object template) {}
    // 删除表
    public static void drop(String table) {}

    // 增加一行数据
    public static void create(String table, Object data) {}
    // 删除一行数据
    public static void delete(String table, Object data) {}
    // 查询一组数据
    public static <T> List<T> read(String table, T data) { return null; }
    // 更新一行数据
    public static void update(String table, Object data) {}
}
