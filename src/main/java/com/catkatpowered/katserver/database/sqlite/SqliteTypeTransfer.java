package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DatabaseTypeTransfer;

/**
 * 用于将 Java 类型数据推导至 Sql 类型
 *
 * @author hanbings
 */
public class SqliteTypeTransfer implements DatabaseTypeTransfer {
    @Override
    public String getDataType(Object data) {
        return null;
    }
}
