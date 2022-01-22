package com.catkatpowered.katserver.database.mysql;

import com.catkatpowered.katserver.log.KatLoggerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 谁在乎 JDBC 版本啊 <br>
 * 一个 try catch 抹平 JDBC 对应 Mysql 版本问题
 *
 * @author hanbings
 */
public class MySqlConnector {
    public static Connection getMysqlConnection(String url, String username, String password) {
        // 先处理 8.x 版本的 Mysql JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException found) {
            // 版本不对 处理版本
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException exception) {
                KatLoggerManager.getLogger("Database Manager").error("load jdbc error.", exception);
            }
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            KatLoggerManager.getLogger("Database Manager").error("load mysql database error.", exception);
        }
        return null;
    }
}
