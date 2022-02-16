package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.mongodb.MongoDBConnector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBDatabaseTest {
    @Test
    public void connect() {
        MongoDBConnector connector = new MongoDBConnector();
        connector.loadDatabase("mongodb://localhost:27017/kat-server",
                "kat-server",
                "kat-server");
        assertNotNull(connector.getConnection().getMongoConnection());
    }
}
