package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.log.KatLogger;
import com.catkatpowered.katserver.network.KatNetwork;
import com.catkatpowered.katserver.storage.KatStorage;

public class KatServer {

    public static void main(String[] args) {
        // 启动网络模块
        KatNetwork.KatNetworkMain();
        // 启动储存模块
        KatStorage.KatStorageMain();

        KatLogger.getLogger(KatMiscConstants.KAT_PROJECT_NAME).info("Started!");
    }
}
