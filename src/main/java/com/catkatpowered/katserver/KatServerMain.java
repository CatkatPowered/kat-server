package com.catkatpowered.katserver;

import com.catkatpowered.katserver.common.constants.KatMiscConstants;
import com.catkatpowered.katserver.config.KatConfig;
import com.catkatpowered.katserver.config.KatConfigManager;
import com.catkatpowered.katserver.database.KatDatabaseManager;
import com.catkatpowered.katserver.event.KatEventManager;
import com.catkatpowered.katserver.extension.KatExtensionManager;
import com.catkatpowered.katserver.network.KatNetworkManager;
import com.catkatpowered.katserver.storage.KatStorageManager;
import com.catkatpowered.katserver.task.KatTaskManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 本项目采用 AGPL v3 进行开源 请遵循开源协议
 */
@Slf4j
public class KatServerMain {

  public static void main(String[] args) {
    // 画大饼
    log.info(KatMiscConstants.KAT_SERVER_LOGO);

    // 启动配置文件模块
    KatConfigManager.init();
    // 启动事件总线模块
    KatEventManager.init();
    // 启动网络模块
    KatNetworkManager.init();
    // 启动数据库
    KatDatabaseManager.init();
    // 启动储存模块
    KatStorageManager.init();
    // 启动任务模块
    KatTaskManager.init();
    // 启动扩展模块
    KatExtensionManager.init();

    // 启动完成
    log.info("Started!");
  }
}
