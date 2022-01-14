package com.catkatpowered.katserver;

import com.catkatpowered.katserver.network.KatNetwork;

public class KatServer {

  public static void main(String[] args) {
    // 加载配置文件
    KatConfig.KatConfigMain();
    // 启动网络服务
    KatNetwork.KatNetworkMain();
  }
}
