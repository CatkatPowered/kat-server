package com.catkatpowered.katserver.tokenpool;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.common.constants.KatConfigNodeConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.var;

/**
 * TODO：需要设计Token池的各种机制
 */
public class KatTokenPool {
    private static KatTokenPool INSTANCE = new KatTokenPool();

    private KatTokenPool() {
    }

    @Getter
    @Setter
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

    /**
     * 
     * 通过传入的<em>action</em>判断对<em>TokenPool</em>的操作
     * 
     * @param action
     */
    public void cleanTokens(ClearAction action) {
        switch (action) {
            case All -> this.tokenPool.clear();
            case Outdated -> {
                var outdateTime = KatServer.KatConfigAPI
                        .<Integer>getConfig(KatConfigNodeConstants.KAT_CONFIG_TOKENPOOL_OUTDATE)
                        .get();
                for (var item : this.tokenPool.entrySet())
                    if ((System.currentTimeMillis() - item.getValue()) >= outdateTime)
                        this.tokenPool.remove(item.getKey());
            }
        }
    }

    public enum ClearAction {
        All, Outdated
    }

}
