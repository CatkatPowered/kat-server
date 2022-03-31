package com.catkatpowered.katserver.task;

import com.catkatpowered.katserver.KatServer.KatConfigAPI;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("unused")
public class KatTask {

    private static final KatTask Instance = new KatTask();

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Integer.parseInt(
                    (String) KatConfigAPI.getConfig(KatConfigNodeConstants.KAT_CONFIG_EXEC_THREADS).get()));

    private KatTask() {
    }

    public static KatTask getInstance() {
        return Instance;
    }

    public Future<?> addTask(Runnable task) {
        return this.executorService.submit(task);
    }

    public <T> Future<T> addTask(Callable<T> task) {
        return this.executorService.submit(task);
    }

    public <T> Future<T> addTask(Runnable task, T result) {
        return this.executorService.submit(task, result);
    }

    public void exec(Runnable task) {
        this.executorService.execute(task);
    }

}
