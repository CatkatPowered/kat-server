package com.catkatpowered.katserver.database.interfaces;

import java.util.List;
import java.util.Map;

public interface DatabaseConnection {
    /**
     * 创建一个数据文档
     *
     * @param collection 目标集合
     * @param data       数据实体
     */
    void create(String collection, Object data);

    /**
     * 创建一打数据集合
     *
     * @param collection 目标集合
     * @param data       数据实体
     */
    void create(String collection, List<Object> data);

    /**
     * 更新一些数据文档
     *
     * @param collection 目标集合
     * @param index      数据索引 需要 new 一个目标对应的 Map kv 值 仅写入索引数据 将自动处理为 Bson
     * @param data       数据实体 需要更新进入数据库的数据实体
     */
    void update(String collection, Map<String, Object> index, Object data);

    /**
     * 删除一些数据文档
     *
     * @param collection 目标集合
     * @param index      数据索引 需要 new 一个目标对应的 Map kv 值 仅写入索引数据 将自动处理为 Bson
     */
    void delete(String collection, Map<String, Object> index);

    /**
     * 查询数据集合 符合的结果将进入 List 列表返回 需要提供泛型数据实体类类型
     *
     * @param collection 目标集合
     * @param index      数据索引 需要 new 一个目标对应的 Map kv 值 仅写入索引数据 将自动处理为 Bson
     * @param type       泛型数据实体类类型
     * @param <T>        返回的数据实体类型
     * @return 数据实体列表
     */
    <T> List<T> read(String collection, Map<String, Object> index, Class<T> type);

    /**
     * 搜索数据集合 符合的结果将进入 List 列表返回 需要提供泛型数据实体类类型
     * 本方法提供根据某一个字段名称的边界以及限制数量进行搜索的操作
     *
     * @param collection 目标集合
     * @param data       字段名称
     * @param top        边界
     * @param bottom     边界
     * @param limit      限制值
     * @param type       泛型数据实体类类型
     * @param <T>        返回的数据实体类型
     * @return 数据实体列表
     */
    <T> List<T> search(String collection, String data, String top, String bottom, int limit, Class<T> type);
}
