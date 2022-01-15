package com.catkatpowered.katserver;

import com.catkatpowered.katserver.network.KatNetwork;
import com.catkatpowered.katserver.storage.KatStorage;

public class KatServer {

  public static void main(String[] args) {
    // 加载配置文件
    KatConfig.KatConfigMain();
    // 启动网络模块
    KatNetwork.KatNetworkMain();
    // 启动储存模块
    KatStorage.KatStorageMain();
  }
}
