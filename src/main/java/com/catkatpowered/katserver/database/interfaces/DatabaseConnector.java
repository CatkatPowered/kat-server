package com.catkatpowered.katserver.database.interfaces;

public interface DatabaseConnector {
  // 加载驱动
  void loadDatabase(String url, String username, String password);
  // 获取链接
  DatabaseConnection getConnection();
    // 断开链接
    void exit();
}
