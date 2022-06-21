package com.catkatpowered.katserver.task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Krysztal
 * @author hanbings
 */
public class KatTaskManager {

    public static void init() {
        KatTask.getInstance();
    }

    public static Future<?> addTask(Runnable task) {
        return KatTask.getInstance().addTask(task);
    }

    public static <T> Future<T> addTask(Callable<T> task) {
        return KatTask.getInstance().addTask(task);
    }

    public static <T> Future<T> addTask(Runnable task, T result) {
        return KatTask.getInstance().addTask(task, result);
    }

    public static void exec(Runnable task) {
        KatTask.getInstance().exec(task);
    }

    @Deprecated
    public static KatTask getInstance() {
        return KatTask.getInstance();
    }

}
