package com.catkatpowered.katserver.storage;

public class KatStorage {

    private static final KatStorage Instance = new KatStorage();

    private KatStorage() {
    }

    public static KatStorage getInstance() {
        return Instance;
    }
}
