package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.catkatpowered.katserver.database.type.DatabaseType;

/**
 * 数据库处理层 桥接数据库
 *
 * @author hanbings
 * @author suibing112233
 */
public class KatDatabaseManager {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        KatDatabase.getInstance();
    }

    // 取得链接器
    public static DatabaseConnector getConnector() {
        return KatDatabase.getInstance().getConnector();
    }

    public static DatabaseConnector getConnector(DatabaseType type) {
        return KatDatabase.getInstance().pickConnector(type);
    }

    // 获取执行器
    public static DatabaseActions getActions() {
        return KatDatabase.getInstance().getActions();
    }

    public static DatabaseActions getActions(DatabaseType type) {
        return KatDatabase.getInstance().pickActions(type);
    }

}
