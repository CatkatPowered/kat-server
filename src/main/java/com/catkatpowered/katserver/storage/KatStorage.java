package com.catkatpowered.katserver.storage;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class KatStorage {
    public static void KatStorageMain() {
        // 注册BouncyCastle算法库
        Security.addProvider(new BouncyCastleProvider());

    }
}
