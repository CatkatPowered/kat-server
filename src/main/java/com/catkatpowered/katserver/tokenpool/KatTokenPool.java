package com.catkatpowered.katserver.tokenpool;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 需要设计Token池的各种机制
 */
public class KatTokenPool {
    private static KatTokenPool INSTANCE = new KatTokenPool();

    private KatTokenPool() {
    }

    private ConcurrentHashMap<String, Date> tokenPool = new ConcurrentHashMap<>();

    public static KatTokenPool getINSTANCE() {
        return INSTANCE;
    }

    public String genToken() {
        return null;
    }

    public boolean destroyToken(String token) {
        return true;
    }

    public void cleanTokens(ClearAction action) {
        switch (action) {
            case CleanAll -> {
            }
            case CleanOutdated -> {
            }
        }
    }

    public enum ClearAction {
        CleanAll, CleanOutdated
    }

}
