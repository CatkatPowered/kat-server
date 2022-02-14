package com.catkatpowered.katserver.database.type;

@SuppressWarnings("SpellCheckingInspection")
public enum DatabaseType {
    MySQL, SQLite, PostGreSQL, MongoDB;

    public static DatabaseType lookup(String database) {
        for (DatabaseType type : DatabaseType.values()) {
            if (type.name().equalsIgnoreCase(database)) {
                return type;
            }
        }
        return DatabaseType.SQLite;
    }
}
