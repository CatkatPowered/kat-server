package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.constants.KatMiscConstants;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.network.KatNetworkManager;
import com.catkatpowered.katserver.storage.KatStorage;
import org.apache.logging.log4j.Logger;

/**
 * 本项目采用 AGPL v3 进行开源 请遵循开源协议
 */
public class KatServerMain {

    static Logger logger = KatServer.KatLoggerAPI.getLogger(KatMiscConstants.KAT_PROJECT_NAME);

    public static void main(String[] args) {

        // 画大饼
        logger.info(KatMiscConstants.KAT_SERVER_LOGO);

        // 启动事件总线模块
        KatEventManager.init();
        // 启动网络模块
        KatNetworkManager.init();
        // 启动数据库
        KatDatabaseManager.init();
        // 启动储存模块
        KatStorage.init();
        // 启动扩展模块
        KatExtensionManager.init();

        // 启动完成
        logger.info("Started!");
    }
}
