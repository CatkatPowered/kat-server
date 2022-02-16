package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.mongodb.MongoDBConnector;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBDatabaseTest {

    // 若配置文件可读取则使用配置文件中的配置进行测试
    String url = KatServer.KatConfigAPI.getConfig("database_url") == null
            ? "mongodb://localhost:27017/kat-server"
            : KatServer.KatConfigAPI.getConfig("database_url").toString();
    String username = KatServer.KatConfigAPI.getConfig("database_username") == null
            ? "kat-server"
            : KatServer.KatConfigAPI.getConfig("database_username").toString();
    String password = KatServer.KatConfigAPI.getConfig("database_password") == null
            ? "kat-server"
            : KatServer.KatConfigAPI.getConfig("database_password").toString();

    @Test
    public void connect() {
        MongoDBConnector connector = new MongoDBConnector();
        connector.loadDatabase(url, username, password);
    }
}
