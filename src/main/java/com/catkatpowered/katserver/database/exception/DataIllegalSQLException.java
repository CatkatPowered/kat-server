package com.catkatpowered.katserver.database.exception;

public class DataIllegalSQLException extends DataProcessingException {
    public DataIllegalSQLException(String message) {
        super(message);
    }

    public DataIllegalSQLException(String message, Throwable cause) {
        super(message, cause);
    }
}
