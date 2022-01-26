package com.catkatpowered.katserver.database.interfaces;

public interface KatDatabaseConnector {
    void loadDatabase(String url, String username, String password);
}
