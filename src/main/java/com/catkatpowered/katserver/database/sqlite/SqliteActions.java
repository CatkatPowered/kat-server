package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.ActionsType;
import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.catkatpowered.katserver.database.interfaces.DatabaseTypeTransfer;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析 sql 并存储为安全的预编译语句 <br>
 * 执行一句语句流程 <br>
 * 1. 初始化预编译缓存 <br>
 * 2. 解析实体类 注入参数 <br>
 * 3. 提交至数据库 <br><br>
 *
 * 初始化预编译时需要 <br>
 * 1. 判断表是否存在 <br>
 * 2. 解析数据实体将 Java 数据类型动态推导为 sql 类型 <br>
 *    数据注解 -> 无注解自然语序数据 读取到的第一个变量为主键 <br>
 * 3. 拼接 sql 进行预编译 <br><br>
 *
 * 解析实体类 <br>
 * 1. 判断是否为主键类型的 CURD 仅有主键作为索引的实体类有预编译缓存 <br>
 * 2. 扫描注解 提取数据 <br>
 *    数据注解 -> 无注解自然语序数据 读取到的第一个变量为主键 <br>
 * 3. 注入到预编译语句
 */
public class SqliteActions implements DatabaseActions {
    // 预编译缓存 key 为表名
    // value 为嵌套 Map
    // - 嵌套 Map key 为 CURD 中的一个操作 value 为预编译的 statement
    Map<String,  Map<ActionsType, PreparedStatement>> mapping = new HashMap<>();
    // 动态类型推导器
    DatabaseTypeTransfer transfer = new SqliteTypeTransfer();

    @Override
    public void create(DatabaseConnection connection, String table, Object data) {
        // 判断是否预编译
        if (mapping.containsKey(table)) {
            if (mapping.get(table).containsKey(ActionsType.Create)) {
                // 解析参数
                PreparedStatement statement = mapping.get(table).get(ActionsType.Create);
                
            }
        }
    }

    @Override
    public void delete(DatabaseConnection connection, String table, Object data) {

    }

    @Override
    public <T> List<T> read(DatabaseConnection connection, String table, T data) {
        return null;
    }

    @Override
    public void update(DatabaseConnection connection, String table, Object data) {

    }
}
