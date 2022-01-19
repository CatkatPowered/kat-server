package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.KatMiscConstants;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.log.KatLoggerManager;
import com.catkatpowered.katserver.network.KatNetworkManager;
import com.catkatpowered.katserver.storage.KatStorageManager;

/**
 * 本项目采用 AGpl v3 进行开源 请遵循开源协议
 */
public class KatServer {

    public static void main(String[] args) {
        // 画大饼
        KatLoggerManager.getLogger(KatMiscConstants.KAT_PROJECT_NAME).info(KatMiscConstants.KAT_SERVER_LOGO);

        // 启动事件总线模块
        KatEventManager.KatEventMain();
        // 启动网络模块
        KatNetworkManager.KatNetworkMain();
        // 启动储存模块
        KatStorageManager.KatStorageMain();
        // 启动扩展模块
        KatExtensionManager.KatExtensionMain();

        // 启动完成
        KatLoggerManager.getLogger(KatMiscConstants.KAT_PROJECT_NAME).info("Started!");
    }
}
