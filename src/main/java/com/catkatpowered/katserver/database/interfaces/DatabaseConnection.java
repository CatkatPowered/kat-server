package com.catkatpowered.katserver.database.interfaces;

import java.sql.Connection;

public interface DatabaseConnection {
    Connection getJdbcConnection();
}
