package com.catkatpowered.katserver.database.exception;

public class DataIllegalAccessException extends DataProcessingException {
    public DataIllegalAccessException(String message) {
        super(message);
    }

    public DataIllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
