package com.catkatpowered.katserver.database.sqlite;
import com.catkatpowered.katserver.database.sqlite.interfaces.SqliteData;
import com.catkatpowered.katserver.database.sqlite.interfaces.SqliteDataTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SqliteEntityParser {
    /**
     * 创建一个数据表
     * @param table 表实体
     * @return sql 语句
     */
    public static String createTable(Class<?> table) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            // 获取表名
            builder.append(table.getAnnotation(SqliteDataTable.class).table()).append(" (");
            Field[] fields = table.getDeclaredFields();
            // 使用一个 flag 绕过第一次有效字段循环 以避免错误添加的分隔符
            boolean isAdd = false;
            boolean isHavePrimary = false;
            // 扫描字段
            for (Field field : fields) {
                // 扫描有效字段
                if (field.isAnnotationPresent(SqliteData.class)) {
                    // 添加分隔符
                    if (isAdd) {
                        builder.append(", ");
                    } else {
                        isAdd = true;
                    }
                    // 获取注解
                    SqliteData data = field.getAnnotation(SqliteData.class);
                    // 字段名 类型 主键 唯一约束 自增 非空
                    builder.append(table.getAnnotation(SqliteDataTable.class).isToUpper()
                            ? getColumnName(field.getName()).toUpperCase(Locale.ROOT) : getColumnName(field.getName()));
                    // 添加类型
                    builder.append(" ").append(data.type());
                    // 判断是否为主键 当然 只允许一个主键
                    if (!isHavePrimary) {
                        if (data.isPrimaryKey()) {
                            builder.append(" PRIMARY KEY");
                            isHavePrimary = true;
                        }
                    }
                    // 唯一约束
                    if (data.isUnique()) {
                        builder.append(" UNIQUE");
                    }
                    // 自增
                    if (data.isAutoincrement()) {
                        builder.append(" AUTOINCREMENT");
                    }
                    // 非空
                    if (data.isNotNull()) {
                        builder.append(" NOT NULL");
                    }
                }
            }
        }
        return builder.append(");").toString();
    }

    /**
     * 删除一个数据表
     * @param table 表实体
     * @return sql 语句
     */
    public static String deleteTable(Class<?> table) {
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE ");
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            builder.append(table.getAnnotation(SqliteDataTable.class).table());
        }
        return builder.append(";").toString();
    }

    /**
     * 创建一行数据
     * @param data 数据实体
     * @return sql 语句
     */
    public static String create(Object data) {
        StringBuilder builder = new StringBuilder();
        List<String> addable = new ArrayList<>();
        builder.append("INSERT INTO ");
        if (data.getClass().isAnnotationPresent(SqliteDataTable.class)) {
            builder.append(data.getClass().getAnnotation(SqliteDataTable.class).table());
            // 遍历字段 获取符合要求的字段
            Field[] fields = data.getClass().getDeclaredFields();
            boolean flag = false;
            builder.append(" (");
            for (Field field : fields) {
                if (field.isAnnotationPresent(SqliteData.class)) {
                    addable.add(field.getName());
                    if (flag) {
                        builder.append(", ");
                    } else {
                        flag = true;
                    }
                    builder.append(data.getClass().getAnnotation(SqliteDataTable.class).isToUpper()
                            ? getColumnName(field.getName()).toUpperCase(Locale.ROOT) : getColumnName(field.getName()));
                }
            }
            builder.append(") VALUE (");
            flag = false;
            // 遍历字段 拼接 get 方法 获取字段值
            for (String add : addable) {
                if (flag) {
                    builder.append(", ");
                } else {
                    flag = true;
                }
                try {
                    Method method = data.getClass().getMethod("get" + captureName(add));
                    method.setAccessible(true);
                    builder.append("'").append(method.invoke(data)).append("'");
                } catch (NoSuchMethodException e) {
                    try {
                        Method method = data.getClass().getMethod("is" + captureName(add));
                        method.setAccessible(true);
                        builder.append("'").append(method.invoke(data)).append("'");
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.append(");").toString();
    }
    /**
     * 更新一行数据
     * @param data 数据实体
     * @return sql 语句
     */
    public static String update(Object data) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        if (data.getClass().isAnnotationPresent(SqliteDataTable.class)) {
            builder.append(data.getClass().getAnnotation(SqliteDataTable.class).table());
            // 遍历字段 获取符合要求的字段
            Field[] fields = data.getClass().getDeclaredFields();
            boolean flag = false;
            String primary = "";
            Object value = "";
            builder.append(" SET ");
            for (Field field : fields) {
                if (flag) {
                    builder.append(", ");
                } else {
                    flag = true;
                }
                if (field.isAnnotationPresent(SqliteData.class)) {
                    if (data.getClass().getAnnotation(SqliteDataTable.class).isToUpper()) {
                        builder.append(field.getName().toUpperCase(Locale.ROOT)).append(" = ");
                    } else {
                        builder.append(field.getName()).append(" = ");
                    }
                    try {
                        Method method = data.getClass().getMethod("get" + captureName(field.getName()));
                        method.setAccessible(true);
                        Object temp = method.invoke(data);
                        if (field.getAnnotation(SqliteData.class).isPrimaryKey()) {
                            primary = field.getName();
                            value = temp;
                        }
                        builder.append("'").append(temp).append("'");
                    } catch (NoSuchMethodException e) {
                        try {
                            Method method = data.getClass().getMethod("is" + captureName(field.getName()));
                            method.setAccessible(true);
                            Object temp = method.invoke(data);
                            if (field.getAnnotation(SqliteData.class).isPrimaryKey()) {
                                primary = field.getName();
                                value = temp;
                            }
                            builder.append("'").append(temp).append("'");
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                            ex.printStackTrace();
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            builder.append(" WHERE ").append(primary).append(" = ").append("'").append(value).append("'");
        }
        return builder.append(";").toString();
    }
    /**
     * 读取一行数据
     * @param data 数据实体
     * @return sql 语句
     */
    public static String read(Object data) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT *");
        if (data.getClass().isAnnotationPresent(SqliteDataTable.class)) {
            // 遍历字段 获取符合要求的字段
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(SqliteData.class)) {
                    if (field.getAnnotation(SqliteData.class).isPrimaryKey()) {
                        builder.append(" FROM ")
                                .append(data.getClass().getAnnotation(SqliteDataTable.class).table())
                                .append(" WHERE ")
                                .append(data.getClass()
                                        .getAnnotation(SqliteDataTable.class).isToUpper() ? field.getName().toUpperCase(Locale.ROOT) : field.getName())
                                .append(" = ");

                        try {
                            Method method = data.getClass().getMethod("get" + captureName(field.getName()));
                            method.setAccessible(true);
                            builder.append("'").append(method.invoke(data)).append("'");
                        } catch (NoSuchMethodException e) {
                            try {
                                Method method = data.getClass().getMethod("is" + captureName(field.getName()));
                                method.setAccessible(true);
                                builder.append("'").append(method.invoke(data)).append("'");
                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                                ex.printStackTrace();
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return builder.append(";").toString();
                    }
                }
            }
        }
        return builder.append(";").toString();
    }
    /**
     * 删除一行数据
     * @param data 数据实体
     * @return sql 语句
     */
    public static String delete(Object data) {
        StringBuilder builder = new StringBuilder();
        if (data.getClass().isAnnotationPresent(SqliteDataTable.class)) {
            // 遍历字段 获取符合要求的字段
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(SqliteData.class)) {
                    if (field.getAnnotation(SqliteData.class).isPrimaryKey()) {
                        builder.append("DELETE FROM ")
                                .append(data.getClass().getAnnotation(SqliteDataTable.class).table())
                                .append(" WHERE ")
                                .append(data.getClass()
                                        .getAnnotation(SqliteDataTable.class).isToUpper() ? field.getName().toUpperCase(Locale.ROOT) : field.getName())
                                .append(" = ");

                        try {
                            Method method = data.getClass().getMethod("get" + captureName(field.getName()));
                            method.setAccessible(true);
                            builder.append("'").append(method.invoke(data)).append("'");
                        } catch (NoSuchMethodException e) {
                            try {
                                Method method = data.getClass().getMethod("is" + captureName(field.getName()));
                                method.setAccessible(true);
                                builder.append("'").append(method.invoke(data)).append("'");
                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                                ex.printStackTrace();
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return builder.append(";").toString();
                    }
                }
            }
        }
        return builder.append(";").toString();
    }

    /**
     * 首字母大写
     * @param word 词
     * @return 结果
     */
    private static String captureName(String word) {
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }

    private static String getColumnName(String field) {
        // 大写字母前使用下划线
        // 大写字母全小写
        StringBuilder builder = new StringBuilder();
        char[] chars = field.toCharArray();
        for (char c : chars) {
            boolean digit = Character.isUpperCase(c);
            String strings = String.valueOf(c);
            String temp = strings.toLowerCase();
            builder.append(digit ? "_" + temp : c);
        }
        return builder.toString();
    }
}