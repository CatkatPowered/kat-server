package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

/**
 * Sqlite 连接器 负责获取 JDBC 连接桥
 *
 * @author hanbings
 */
@Slf4j
public class SQLiteConnector implements DatabaseConnector {

    Connection connection;

    @Override
    public void loadDatabase(String url, String username, String password) {
        // 加载驱动
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            log.error("load sqlite jdbc error.", exception);
        }
        // 获取连接
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            log.error("load database error.", exception);
        }
    }

    @Override
    public DatabaseConnection getConnection() {
        return new SQLiteConnection(connection);
    }

    @Override
    public void exit() {
        try {
            connection.close();
        } catch (SQLException exception) {
            log.error(String.valueOf(exception));
        }
    }
}
