package com.catkatpowered.katserver.database.sqlite;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;
import java.sql.Connection;

public class SQLiteConnection implements DatabaseConnection {
  Connection jdbcConnection;

  private SQLiteConnection() {}

  public SQLiteConnection(Connection connection) {
    jdbcConnection = connection;
  }

  @Override
  public Connection getJdbcConnection() {
    return jdbcConnection;
  }
}
