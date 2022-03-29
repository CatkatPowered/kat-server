package com.catkatpowered.katserver.tokenpool;

public class KatTokenPoolManager {

    public static String genToken() {
        return KatTokenPool.getINSTANCE().genToken();
    }

}
