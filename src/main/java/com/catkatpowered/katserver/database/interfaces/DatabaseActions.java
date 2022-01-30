package com.catkatpowered.katserver.database.interfaces;

public interface DatabaseActions {
    // 检查表是否存在
    void check(String table);
    // 创建表
    void table(String table, Object template);
    // 删除表
    void drop(String table);

    // 增加一行数据
    void create(String table, Object data);
    // 删除一行数据
    void delete(String table, Object data);
    // 查询一行数据
    void read(String table, Object data);
    // 更新一行数据
    void update(String table, Object data);
}
