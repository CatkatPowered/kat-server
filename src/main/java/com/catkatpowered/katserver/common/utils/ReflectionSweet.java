package com.catkatpowered.katserver.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射工具类 流式调用 <br>
 * 根据返回值或返回工具类引用分为两类操作 <br>
 * 流操作和返回操作 <br>
 * 流操作返回工具类引用 可以继续调用 <br>
 * 咱是说如果 如果反射也能这么爽 ：D<br>
 * 返回操作值
 *
 * @author hanbings
 */
@Slf4j
@SuppressWarnings("unused")
public class ReflectionSweet {
    Class<?> clazz = this.getClass();

    /**
     * 初始化一个类型
     * @param name 类名
     * @return 工具类引用
     */
    public ReflectionSweet load(String name) {
        try {
            clazz = ReflectionSweet.class.getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException", e);
        }
        return this;
    }

    /**
     * 给予访问权限
     * @return 工具类引用
     */
    public ReflectionSweet access() {
        Arrays.stream(clazz.getFields()).forEach(field -> field.setAccessible(true));
        Arrays.stream(clazz.getMethods()).forEach(method -> method.setAccessible(true));
        return this;
    }

    /**
     * 给予类型某个变量或某个方法访问权限
     * @param name 变量名
     * @return 工具类引用
     */
    public ReflectionSweet access(String name) {
        Arrays.stream(clazz.getFields())
                .filter(field -> field.getName().equals(name))
                .forEach(field -> field.setAccessible(true));
        Arrays.stream(clazz.getMethods())
                .filter(method -> method.getName().equals(name))
                .forEach(method -> method.setAccessible(true));
        return this;
    }

    /**
     * 获取类型的所有变量
     * @return 变量数组
     */
    public Field[] fields() {
        return clazz.getFields();
    }

    /**
     * 在全部的 类型 / 变量 / 方法 范围内 <br>
     * 判断类型 / 变量 / 方法是否包含某个注解
     */
    public boolean annotation(Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(annotation)) ||
                Arrays.stream(clazz.getDeclaredMethods())
                        .anyMatch(method -> method.isAnnotationPresent(annotation));
    }

    /**
     * 给定一个 类型 / 变量 / 方法 <br>
     * 在给定的范围内判断是否包含某个注解
     */
    public boolean annotation(Object object, Class<? extends Annotation> annotation) {
        // 用过滤器过滤 object 到底是类型还是变量还是方法
        if (object instanceof Field) {
            return ((Field) object).isAnnotationPresent(annotation);
        }
        if (object instanceof Method) {
            return ((Method) object).isAnnotationPresent(annotation);
        }
        if (object instanceof Class) {
            return ((Class<?>) object).isAnnotationPresent(annotation);
        }
        return false;
    }

    /**
     * 判断类型是否包含某个注解
     * @param annotation 注解
     * @return 是否包含
     */
    public boolean annotationClass(Class<? extends Annotation> annotation) {
        return clazz.isAnnotationPresent(annotation);
    }

    /**
     * 获取包含某个注解的变量
     * @param annotation 注解
     * @return 变量数组
     */
    public Field[] annotationFields(Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .toArray(Field[]::new);
    }

    /**
     * 获取包含某个注解的方法
     * @param annotation 注解
     * @return 方法数组
     */
    public Method[] annotationMethods(Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toArray(Method[]::new);
    }
}
