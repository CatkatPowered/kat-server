package com.catkatpowered.katserver.storage;

import java.util.HashMap;

import com.catkatpowered.katserver.common.constants.KatStorageTypeConstants;
import com.catkatpowered.katserver.storage.providers.KatStorageProvider;
import com.catkatpowered.katserver.storage.providers.local.LocalProvider;

import jdk.jfr.Experimental;

@Experimental
public class KatStorage {

    private static final KatStorage Instance = new KatStorage();

    private KatStorage() {
    }

    public static KatStorage getInstance() {
        return Instance;
    }

    public void addStorage(String resourceName, KatStorageProvider storage) {
        this.storageTarget.put(resourceName, storage);
    }

    private final HashMap<String, KatStorageProvider> storageTarget = new HashMap<String, KatStorageProvider>() {
        {
            storageTarget.put(KatStorageTypeConstants.KAT_STORAGE_TYPE_LOCAL, new LocalProvider());
        }
    };

}
