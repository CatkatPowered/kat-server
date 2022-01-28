package com.catkatpowered.katserver.database.interfaces;

public interface KatDatabaseController {
    // 检查表是否存在
    String check(String table);
    // 创建表
    String table(String table, Object template);
    // 删除表
    String drop(String table);

    // 增加一行数据
    String create(String table, Object data);
    // 删除一行数据
    String delete(String table, Object data);
    // 查询一行数据
    String read(String table, Object data);
    // 更新一行数据
    String update(String table, Object data);
}
