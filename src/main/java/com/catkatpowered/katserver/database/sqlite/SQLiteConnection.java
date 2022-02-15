package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;

public class SQLiteConnection implements DatabaseConnection {
  Connection jdbcConnection;

  @SuppressWarnings("unused")
  private SQLiteConnection() {}

  public SQLiteConnection(Connection connection) {
    jdbcConnection = connection;
  }

  @Override
  public Connection getJdbcConnection() {
    return jdbcConnection;
  }

  @Override @Deprecated @SuppressWarnings("SpellCheckingInspection")
  public MongoDatabase getMongoConnection() {
    return null;
  }
}
