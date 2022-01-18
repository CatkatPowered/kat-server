package com.catkatpowered.katserver.storage;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.log.KatLogger;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KatStorageManager {

    public static void KatStorageMain() {
        // ShaUtils 轮子已造

        // 加载数据库驱动
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            KatLogger.getLogger().fatal(e.getStackTrace());
        }
        // 创建数据库
        String databasePath = "JDBC:sqlite:" + KatMiscConstants.KAT_DATABASE_PATH;
        if (!new File(databasePath).exists()) {
            try {
                KatMessageStorage.getInstance().setDatabaseConnection(DriverManager.getConnection(databasePath));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            // TODO: 创建表等操作
        }
        // 连接数据库
        try {
            KatMessageStorage.getInstance().setDatabaseConnection(DriverManager.getConnection(databasePath));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
