package com.catkatpowered.katserver.storage;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class KatStorage {

    public static void KatStorageMain() {
        // 注册BouncyCastle算法库
        Security.addProvider(new BouncyCastleProvider());

    }
}
