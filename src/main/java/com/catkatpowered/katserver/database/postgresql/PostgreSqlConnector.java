package com.catkatpowered.katserver.database.postgresql;

import com.catkatpowered.katserver.KatServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("SpellCheckingInspection")
public class PostgreSqlConnector {
    public Connection getPostgreSqlConnection(String url, String username, String password) {
        var logger = KatServer.KatLoggerAPI.getLogger("Database Manager");

        // 先处理 8.x 版本的 Mysql JDBC
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException found) {
            // 版本不对 处理版本
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException exception) {
                logger.error("load jdbc error.", exception);
            }
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            logger.error("load mysql database error.", exception);
        }
        return null;
    }
}
