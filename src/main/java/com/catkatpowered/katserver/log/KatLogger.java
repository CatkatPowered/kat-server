package com.catkatpowered.katserver.log;

import java.util.HashMap;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLogger {

    private static final KatLogger INSTANCE = new KatLogger();

    @Getter
    private final HashMap<String, Logger> loggerManager = new HashMap<String, Logger>();

    private KatLogger() {
    }

    public static KatLogger getInstance() {
        return INSTANCE;
    }

    public static Logger getLogger(String name) {
        var logger = KatLogger.getInstance().loggerManager.get(name);

        if (logger == null) {
            KatLogger.getInstance().loggerManager.put(name, LogManager.getLogger(name));
        }
        return KatLogger.getInstance().loggerManager.get(name);
    }

    public static Logger getLogger() {
        return LogManager.getLogger();
    }
}
