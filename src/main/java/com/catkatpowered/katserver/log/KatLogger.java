package com.catkatpowered.katserver.log;

import java.util.HashMap;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLogger {

    private static final Logger INSTANCE = LogManager.getLogger();

    @Getter
    private final HashMap<String, Logger> loggerManager = new HashMap<String, Logger>();

    private KatLogger() {
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(String name) {
        var logger = this.loggerManager.get(name);

        if (logger == null) {
            this.loggerManager.put(name, LogManager.getLogger(name));
        }
        return this.loggerManager.get(name);
    }

    public static Logger getLogger() {
        return LogManager.getLogger();
    }
}
