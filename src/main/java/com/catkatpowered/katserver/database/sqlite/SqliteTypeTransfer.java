package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DataType;
import com.catkatpowered.katserver.database.interfaces.DatabaseTypeTransfer;

/**
 * 用于将 Java 类型数据推导至 Sql 类型
 *
 * @author hanbings
 */
public class SqliteTypeTransfer implements DatabaseTypeTransfer {
    @Override
    public String getDataType(Object data) {
        if (data == null) {
            return DataType.Sqlite.NULL;
        }
        return switch (data.getClass().getSimpleName()) {
            case "String" -> DataType.Sqlite.TEXT;
            case "Integer" -> DataType.Sqlite.INTEGER;
            case "Boolean" -> DataType.Sqlite.REAL;
            default -> DataType.Sqlite.BLOB;
        };
    }
}
