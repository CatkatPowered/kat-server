package com.catkatpowered.katserver.database.interfaces;

import com.mongodb.client.MongoDatabase;

import java.sql.Connection;

/**
 * DataConnection 接口和实现负责存储链接
 *
 * @author hanbings
 */
@SuppressWarnings("SpellCheckingInspection")
public interface DatabaseConnection {
    Connection getJdbcConnection();
    MongoDatabase getMongoConnection();
}