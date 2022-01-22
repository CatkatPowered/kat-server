package com.catkatpowered.katserver.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLoggerManager {

    public static Logger getLogger() {
        return LogManager.getLogger();
    }

    public static Logger getLogger(String loggerName) {
        return KatLogger.getInstance().getLogger(loggerName);
    }
}
