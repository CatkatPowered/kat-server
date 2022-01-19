package com.catkatpowered.katserver.log;

import java.util.HashMap;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLoggerManager {

    private static final KatLoggerManager INSTANCE = new KatLoggerManager();

    @Getter
    private final HashMap<String, Logger> loggerManager = new HashMap<String, Logger>();

    private KatLoggerManager() {
    }

    public static KatLoggerManager getInstance() {
        return INSTANCE;
    }

    public static Logger getLogger(String name) {
        var logger = KatLoggerManager.getInstance().loggerManager.get(name);

        if (logger == null) {
            KatLoggerManager.getInstance().loggerManager.put(name, LogManager.getLogger(name));
        }
        return KatLoggerManager.getInstance().loggerManager.get(name);
    }

    public static Logger getLogger() {
        return LogManager.getLogger();
    }
}
