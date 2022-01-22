package com.catkatpowered.katserver.database.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBConnector {

    /**
     * 获取数据库对象
     *
     * @param mongoClient Mongo Client
     * @param database    数据库名字
     * @return MongoDatabase
     */
    public static MongoDatabase getMongoDatabase(MongoClient mongoClient, String database) {
        return mongoClient.getDatabase(database);
    }

    /**
     * 一步到位获取数据库对象
     *
     * @param address  地址
     * @param port     端口
     * @param username 数据库用户名
     * @param database 数据库名字
     * @param password 数据库密码
     * @return 返回一步到位的数据库对象
     */
    public static MongoDatabase getMongoDatabase(String address, int port, String username, String database, String password) {
        return MongoDBConnector.getMongoClient(MongoDBConnector.getServerAddress(address, port)
                , MongoDBConnector.getMongoCredential(username, database, password)
                , MongoDBConnector.getMongoClientOptions()
        ).getDatabase(database);
    }

    /**
     * 获取 Mongo Client 使用默认端口 27017 无密码保护
     *
     * @param address 地址
     * @return Mongo Client 对象
     */
    public static MongoClient getMongoClient(String address) {
        return new MongoClient(address, 27017);
    }

    /**
     * 获取 Mongo Client
     *
     * @param address 地址
     * @param port    端口
     * @return Mongo Client 对象
     */
    public static MongoClient getMongoClient(String address, int port) {
        return new MongoClient(address, port);
    }

    /**
     * 取 Mongo Client 使用密码认证 <br>
     * 使用 MongoDBUtils.getMongoCredential() 生成 MongoCredential <br>
     * 使用 MongoDBUtils.getMongoClientOption() 生成 预先设定好的 MongoClientOption <br>
     * 连接和 Socket 超时时间 5000 ms 最大链接主机数 30
     *
     * @param address    地址
     * @param credential 认证
     * @param options    选项
     * @return Mongo Client 对象
     */
    public static MongoClient getMongoClient(ServerAddress address, MongoCredential credential, MongoClientOptions options) {
        return new MongoClient(address, credential, options);
    }

    /**
     * 获取 ServerAddress 对象 使用默认端口 27017
     *
     * @param address 地址
     * @return ServerAddress
     */
    public static ServerAddress getServerAddress(String address) {
        return new ServerAddress(address, 27017);
    }

    /**
     * 获取 ServerAddress 对象
     *
     * @param address 地址
     * @param port    端口
     * @return ServerAddress
     */
    public static ServerAddress getServerAddress(String address, int port) {
        return new ServerAddress(address, port);
    }

    /**
     * 获取 MongoCredential
     *
     * @param username 数据库用户名
     * @param database 数据库名字
     * @param password 数据库密码
     * @return MongoCredential
     */
    public static MongoCredential getMongoCredential(String username, String database, String password) {
        return MongoCredential.createScramSha1Credential(username, database, password.toCharArray());
    }

    /**
     * 获取 MongoCredential
     *
     * @param username 数据库用户名
     * @param database 数据库名字
     * @param password 密码 char 数组 使用 char 数组 能在密码使用后能及时从内存中抹除
     * @return MongoCredential
     */
    public static MongoCredential getMongoCredential(String username, String database, char[] password) {
        return MongoCredential.createScramSha1Credential(username, database, password);
    }

    /**
     * 提供一个选项类 连接和 Socket 超时时间 5000 ms 最大链接主机数 30
     *
     * @return MongoClientOptions
     */
    public static MongoClientOptions getMongoClientOptions() {
        return MongoClientOptions.builder()
                .connectTimeout(5000)
                .socketTimeout(5000)
                .connectionsPerHost(30)
                .build();
    }
}
