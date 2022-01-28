package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.KatDatabaseConnector;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sqlite 连接器 负责获取 JDBC 连接桥
 *
 * @author hanbings
 */
public class SqliteConnector implements KatDatabaseConnector {
    Logger logger = KatServer.KatLoggerAPI.getLogger("Database Manager");
    Connection connection;

    @Override
    public void loadDatabase(String url, String username, String password) {
        // 加载驱动
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            logger.error("load sqlite jdbc error.", exception);
        }
        // 获取连接
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            logger.error("load database error.", exception);
        }
    }

    @Override
    public void execute(String sql) {
        
    }

    @Override
    public void exit() {

    }
}
