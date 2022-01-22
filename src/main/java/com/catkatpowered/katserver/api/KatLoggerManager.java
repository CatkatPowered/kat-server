package com.catkatpowered.katserver.api;

import com.catkatpowered.katserver.log.KatLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLoggerManager {

    public static Logger getLogger(String name) {
        var lManager = KatLogger.getInstance().getLoggerManager();

        var logger = lManager.get(name);

        if (logger == null) {
            logger = LogManager.getLogger(name);
            lManager.put(name, logger);
            KatLogger.getInstance().setLoggerManager(lManager);
        }
        return logger;
    }

    public static Logger getLogger() {
        return LogManager.getLogger();
    }
}
