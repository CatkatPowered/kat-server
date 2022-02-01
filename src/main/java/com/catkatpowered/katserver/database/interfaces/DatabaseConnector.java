package com.catkatpowered.katserver.database.interfaces;

/**
 * DataConnector 接口和实现负责加载数据库实现
 * 创建数据库执行链接以及管理链接池
 *
 * @author hanbings
 */
public interface DatabaseConnector {
  // 加载驱动
  void loadDatabase(String url, String username, String password);
  // 获取链接
  DatabaseConnection getConnection();
    // 断开链接
    void exit();
}
