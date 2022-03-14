package com.catkatpowered.katserver.database.postgresql;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("SpellCheckingInspection")
@Slf4j
public class PostGreSQLConnector {

    Connection connection;

    public void loadDatabase(String url, String username, String password) {
        // 先处理 8.x 版本的 Mysql JDBC
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException found) {
            // 版本不对 处理版本
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException exception) {
                log.error("load jdbc error.", exception);
            }
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            log.error("load mysql database error.", exception);
        }
    }
}
