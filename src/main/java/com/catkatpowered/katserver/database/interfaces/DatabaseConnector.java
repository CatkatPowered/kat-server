package com.catkatpowered.katserver.database.interfaces;

import java.sql.Connection;

public interface DatabaseConnector {
    // 加载驱动 取得链接
    void loadDatabase(String url, String username, String password);
    // 断开链接
    void exit();
}
