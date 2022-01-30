package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;

import java.sql.Connection;

public class SqliteConnection implements DatabaseConnection {
  Connection jdbcConnection;

  private SqliteConnection() {}

  public SqliteConnection(Connection connection) {
    jdbcConnection = connection;
  }

  @Override
  public Connection getJdbcConnection() {
    return jdbcConnection;
  }
}
