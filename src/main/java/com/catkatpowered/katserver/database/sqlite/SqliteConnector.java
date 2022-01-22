package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.api.KatLoggerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sqlite 连接器 负责获取 JDBC 连接桥
 *
 * @author hanbings
 */
public class SqliteConnector {
    public static Connection getSqliteConnection(String path) {
        // 加载驱动
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            KatLoggerManager.getLogger("Database Manager").error("load sqlite jdbc error.", exception);
        }
        // 获取连接
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (SQLException exception) {
            KatLoggerManager.getLogger("Database Manager").error("load database error.", exception);
        }
        return null;
    }
}
