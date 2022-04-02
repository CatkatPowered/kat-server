package com.catkatpowered.katserver.database.interfaces;

public interface DatabaseConnector {
    void open();

    void close();

    DatabaseConnection connection();
}
