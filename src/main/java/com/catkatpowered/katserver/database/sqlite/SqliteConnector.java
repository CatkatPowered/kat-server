package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.KatServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sqlite 连接器 负责获取 JDBC 连接桥
 *
 * @author hanbings
 */
public class SqliteConnector {

    public Connection getSqliteConnection(String path) {
        var logger = KatServer.KatLoggerAPI.getLogger("Database Manager");

        // 加载驱动
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            logger.error("load sqlite jdbc error.", exception);
        }
        // 获取连接
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (SQLException exception) {
            logger.error("load database error.", exception);
        }
        return null;
    }
}
