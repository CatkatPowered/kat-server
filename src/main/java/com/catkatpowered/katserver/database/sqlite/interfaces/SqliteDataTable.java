package com.catkatpowered.katserver.database.sqlite.interfaces;

import java.lang.annotation.*;

/**
 * 用于标记实体类的注解 <br>
 * 被此注解标记后 使用对应数据库的 sql builder 传入实体类即可自动生成 sql 语句 <br>
 * 有需要时 使用 sql check 来检查恶意参数 <br>
 * <p>
 * 注解有三个参数 <br>
 * table - 字符串 数据表名
 * isToUpper - 布尔 是否需要全部转为大写 默认为 false 即保持字段原本的大小
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SqliteDataTable {
    String table() default "table";

    boolean isToUpper() default false;
}
