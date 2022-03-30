package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.mongodb.MongoDBConnector;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SpellCheckingInspection")
public class TestMongoDBDatabase {
    @Test
    public void connect() {
        // 生成连接器
        MongoDBConnector connector = new MongoDBConnector();
        connector.loadDatabase("mongodb://localhost:27017/kat-server", null, null);

        // 创建数据库以测试连接
        MongoDatabase database = connector.getConnection().getMongoConnection();
        database.createCollection("kat-server");
    }
}