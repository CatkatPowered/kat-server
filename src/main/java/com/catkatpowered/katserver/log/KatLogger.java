package com.catkatpowered.katserver.log;

import lombok.Getter;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class KatLogger {
    private static final KatLogger INSTANCE = new KatLogger();
    @Getter
    private final HashMap<String, Logger> loggerHashMap = new HashMap<>();

    private KatLogger() {}

    public static KatLogger getInstance() {
        return INSTANCE;
    }
}
