package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.log.KatLogger;
import com.catkatpowered.katserver.network.KatNetwork;
import com.catkatpowered.katserver.storage.KatStorage;

/**
 * 本项目采用 AGpl v3 进行开源 请遵循开源协议
 */
public class KatServer {

    public static void main(String[] args) {
        // 画大饼
        KatLogger.getLogger(KatMiscConstants.KAT_PROJECT_NAME).info(KatMiscConstants.KAT_SERVER_LOGO);

        // 启动网络模块
        KatNetwork.KatNetworkMain();
        // 启动储存模块
        KatStorage.KatStorageMain();

        // 启动完成
        KatLogger.getLogger(KatMiscConstants.KAT_PROJECT_NAME).info("Started!");
    }
}
