package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.KatServer;
import com.catkatpowered.katserver.database.interfaces.DatabaseType;

import java.sql.Connection;

public class KatDatabaseConnector {
     static DatabaseType type = DatabaseType.SQLite;
     static Connection connection;

     public static void loadDatabase() {
         type = DatabaseType.lookup((String) KatServer.KatConfigAPI.getConfig("database_type"));
     }
}
