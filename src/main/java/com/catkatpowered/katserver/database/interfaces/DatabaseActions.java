package com.catkatpowered.katserver.database.interfaces;

import java.sql.Connection;
import java.util.List;

public interface DatabaseActions {
    // 检查表是否存在
    boolean check(DatabaseConnection connection, String table);
    // 创建表
    void table(DatabaseConnection connection, String table, Object template);
    // 删除表
    void drop(DatabaseConnection connection, String table);

    // 增加一行数据
    void create(DatabaseConnection connection, String table, Object data);
    // 删除一行数据
    void delete(DatabaseConnection connection, String table, Object data);
    // 查询一组数据
    <T> List<T> read(DatabaseConnection connection, String table, T data);
    // 更新一行数据
    void update(DatabaseConnection connection, String table, Object data);
}
