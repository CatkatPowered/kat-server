package com.catkatpowered.katserver.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KatLogger {
    private static final Logger INSTANCE = LogManager.getLogger();

    private KatLogger() {}

    public static Logger getInstance() {
        return INSTANCE;
    }
}
