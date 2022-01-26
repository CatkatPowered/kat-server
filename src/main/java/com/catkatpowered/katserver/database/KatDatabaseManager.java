package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.mysql.MySqlConnector;
import com.catkatpowered.katserver.database.sqlite.SqliteConnector;

import java.sql.Connection;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 */
public class KatDatabaseManager {

    public static void init() {}

    public static Connection getSqliteConnection(String database) {
        return SqliteConnector.getSqliteConnection(database);
    }

    public static Connection getMysqlConnection(String url, String username, String password) {
        return MySqlConnector.getMysqlConnection(url, username, password);
    }
}
