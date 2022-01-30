package com.catkatpowered.katserver.database.interfaces;

/**
 * 被 Metadata 注解后 将一个字段标记为被扫描字段 <br>
 * 即使用此注解标记将被转换为数据库对象 <br>
 * <p>
 * 注解参数
 * type - 字符串 数据库的类型 默认为 blob
 * isNotNull - 布尔 是否不为空 默认为 false
 * isPrimaryKey - 布尔 是否为主键 默认为 false
 * isUnique - 是否添加唯一约束 默认为 false
 * isAutoincrement - 是否为自增量 默认为 false
 */
public @interface SqliteMetadata {
    String name();

    String type() default DataType.Sqlite.BLOB;

    boolean isNotNull() default false;

    boolean isPrimaryKey() default false;

    boolean isUnique() default false;

    boolean isAutoincrement() default false;
}
