package com.catkatpowered.katserver.database.mongodb;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBConnection implements DatabaseConnection {
    MongoClient mongoClient;
    String database;

    @SuppressWarnings("unused")
    private MongoDBConnection() {
    }

    public MongoDBConnection(MongoClient client, String database) {
        this.mongoClient = client;
        this.database = database;
    }

    @Override
    @Deprecated
    public Connection getJdbcConnection() {
        return null;
    }

    @Override
    public MongoDatabase getMongoConnection() {
        return mongoClient.getDatabase(database);
    }
}