package com.catkatpowered.katserver.database.mongodb;

import com.catkatpowered.katserver.database.interfaces.DatabaseActions;
import com.catkatpowered.katserver.database.interfaces.DatabaseConnection;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class MongoDBActions implements DatabaseActions {
    @Override
    public <T> void create(DatabaseConnection connection, String table, T data) {

    }

    @Override
    public <T> void delete(DatabaseConnection connection, String table, T data) {

    }

    @Override
    public <T> List<T> read(DatabaseConnection connection, String table, T data) {
        return null;
    }

    @Override
    public <T> void update(DatabaseConnection connection, String table, T data) {

    }
}