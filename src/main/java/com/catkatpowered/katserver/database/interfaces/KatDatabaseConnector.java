package com.catkatpowered.katserver.database.interfaces;

public interface KatDatabaseConnector {
    // 加载驱动 取得链接
    void loadDatabase(String url, String username, String password);
    // 执行一句 sql 包括安全检查
    void execute(String sql);
    // 断开链接
    void exit();
}
