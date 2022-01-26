package com.catkatpowered.katserver.database.interfaces;

@SuppressWarnings("SpellCheckingInspection")
public enum DatabaseType {
    MySQL, SQLite, PostgreSQL, MongoDB;

    public static DatabaseType lookup(String database) {
        for (DatabaseType type : DatabaseType.values()) {
            if (type.name().equalsIgnoreCase(database)) {
                return type;
            }
        }
        return DatabaseType.SQLite;
    }
}
