package com.catkatpowered.katserver.database.interfaces;

import java.sql.Connection;

/**
 * DataConnection 接口和实现负责存储链接
 *
 * @author hanbings
 */
public interface DatabaseConnection {
    Connection getJdbcConnection();
}
