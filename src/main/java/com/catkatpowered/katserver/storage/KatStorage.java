package com.catkatpowered.katserver.storage;

import jdk.jfr.Experimental;

@Experimental
public class KatStorage {

    private static final KatStorage Instance = new KatStorage();

    private KatStorage() {
    }

    public static KatStorage getInstance() {
        return Instance;
    }
}
