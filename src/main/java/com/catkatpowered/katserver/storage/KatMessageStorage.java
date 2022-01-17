package com.catkatpowered.katserver.storage;

import lombok.Setter;

import java.sql.Connection;

public class KatMessageStorage {
    private final static KatMessageStorage Instance = new KatMessageStorage();
    @Setter
    private Connection databaseConnection;

    public static KatMessageStorage getInstance() {return Instance;}
}
