package com.catkatpowered.katserver.tokenpool;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO：需要设计Token池的各种机制
 */
public class KatTokenPool {
    private static KatTokenPool INSTANCE = new KatTokenPool();

    private KatTokenPool() {
    }

    private ConcurrentHashMap<String, Long> tokenPool = new ConcurrentHashMap<>();

    public static KatTokenPool getINSTANCE() {
        return INSTANCE;
    }

    public String newToken() {
        var uuid = UUID.randomUUID();
        this.tokenPool.put(uuid.toString(), System.currentTimeMillis());
        return uuid.toString();
    }

    public boolean checkToken(String token) {
        return this.tokenPool.containsKey(token);
    }

    public boolean destroyToken(String token) {
        return !Optional.of(this.tokenPool.remove(token)).isEmpty();
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
