package com.catkatpowered.katserver.database.mongodb;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBConnector implements DatabaseConnector {

    MongoClient connection;
    String database;

    @Override
    public void loadDatabase(String url, String username, String password) {
        MongoClientURI uri = new MongoClientURI(url);
        connection = new MongoClient(
                new ServerAddress(uri.getURI()),
                MongoCredential.createCredential(
                        uri.getUsername() != null ? uri.getUsername() : username,
                        uri.getDatabase() != null ? uri.getDatabase() : "test",
                        uri.getPassword() != null ? uri.getPassword() : password.toCharArray()
                ),
                uri.getOptions()
        );
        database = uri.getDatabase();
    }

    @Override
    public DatabaseConnection getConnection() {
        return new MongoDBConnection(connection, database);
    }

    @Override
    public void exit() {
        connection.close();
    }
}
