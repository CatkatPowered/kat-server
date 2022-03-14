package com.catkatpowered.katserver.database.exception;

/**
 * 封装异常，用于处理数据库操作时的异常
 *
 * @author hanbings
 */
public class DataProcessingException extends Exception {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
