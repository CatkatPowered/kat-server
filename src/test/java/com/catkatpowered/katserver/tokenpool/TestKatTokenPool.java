package com.catkatpowered.katserver.tokenpool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestKatTokenPool {
    @Test
    public void genToken() {
        var token = KatTokenPool.getINSTANCE().newToken();
        assertEquals(true, KatTokenPool.getINSTANCE().checkToken(token));
    }
}
