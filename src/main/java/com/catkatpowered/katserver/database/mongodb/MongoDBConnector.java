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
        String host = "127.0.0.1";
        int port = 27017;

        try {
            URI uri = new URI(url);
            host = uri.getHost();
            port = uri.getPort();
        } catch (URISyntaxException exception) {
            log.error(exception.getMessage());
        }

        // 参数或 url 其中一个不为空则使用参数或 url
        if ((username == null || password == null)
                && (resource.getUsername() == null || resource.getPassword() == null)) {
            log.warn("No username or password provided. " +
                    "This is not secure, and it is very likely that the physical server " +
                    "can be compromised without the firewall being turned on.");
            connection = new MongoClient(new ServerAddress(host, port), resource.getOptions());
        } else {
            connection = new MongoClient(
                    new ServerAddress(host, port),
                    // url 的用户名和密码 或 参数的用户名和密码
                    // 按配置文件说明 url 的用户名和密码可不填写 故首选读取参数
                    MongoCredential.createCredential(
                            username != null ? username : resource.getUsername(),
                            resource.getDatabase() != null ? resource.getDatabase() : "test",
                            password != null ? password.toCharArray() : resource.getPassword()
                    ),
                    resource.getOptions()
            );
        }

        database = resource.getDatabase();
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