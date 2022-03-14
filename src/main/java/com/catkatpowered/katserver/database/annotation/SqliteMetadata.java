package com.catkatpowered.katserver.database.annotation;

import com.catkatpowered.katserver.database.type.DataType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被 Metadata 注解后 将一个字段标记为被扫描字段 <br>
 * 即使用此注解标记将被转换为数据库对象 <br>
 * <p>
 * 注解参数
 * name - 表名 无默认值 使用注解必须提供表名 不使用注解则由变量名全小写作为表名
 * type - 字符串 数据库的类型 默认为 blob
 * isNotNull - 布尔 是否不为空 默认为 false
 * isPrimaryKey - 布尔 是否为主键 默认为 false
 * isUnique - 是否添加唯一约束 默认为 false
 * isAutoincrement - 是否为自增量 默认为 false
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqliteMetadata {
    String name();

    String type() default DataType.Sqlite.BLOB;

    boolean isNotNull() default false;

    boolean isPrimaryKey() default false;

    boolean isUnique() default false;

    boolean isAutoincrement() default false;
}
