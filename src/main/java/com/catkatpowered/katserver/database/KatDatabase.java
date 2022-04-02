package com.catkatpowered.katserver.database;

import com.catkatpowered.katserver.database.interfaces.DatabaseConnector;

public class KatDatabase {

    @SuppressWarnings("all")
    private static final KatDatabase Instance = new KatDatabase();
    DatabaseConnector connector;

    public static KatDatabase getInstance() {
        return Instance;
    }

    public void register(DatabaseConnector connector) {
        this.connector = connector;
        this.connector.open();
    }
}