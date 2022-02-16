package com.catkatpowered.katserver.database.mongodb;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public class MongoDBConnector implements DatabaseConnector {

    MongoClient connection;
    String database;

    @Override
    public void loadDatabase(String url, String username, String password) {
        MongoClientURI resource = new MongoClientURI(url);
        try {
            URI uri = new URI(url);
            connection = new MongoClient(
                    uri.getPort() == -1 ? new ServerAddress(uri.getHost()) : new ServerAddress(uri.getHost(), uri.getPort()),
                    MongoCredential.createCredential(
                            resource.getUsername() != null ? resource.getUsername() : username,
                            resource.getDatabase() != null ? resource.getDatabase() : "test",
                            resource.getPassword() != null ? resource.getPassword() : password.toCharArray()
                    ),
                    resource.getOptions()
            );
            database = resource.getDatabase();
        } catch (URISyntaxException exception) {
            log.error(exception.getMessage());
        }
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